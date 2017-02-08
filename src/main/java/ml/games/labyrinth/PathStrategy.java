package ml.games.labyrinth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public interface PathStrategy {

    public List<Position> findPath(Labyrinth labyrinth, Position start, Position end);
    
    /**
     * Find a path whose length is equal to the minimum possible length for a continuous series of adjacent Positions from start to end with no concern for obstructions
     */
    public static final PathStrategy DEFAULT = new PathStrategy() {

        @Override
        public List<Position> findPath(Labyrinth labyrinth, Position start, Position end) {
            Position traveler = start;
            List<Position> path = new ArrayList<Position>();
            path.add(start);

            while (!traveler.equals(end)) {
                Set<Position> surrounding = traveler.getSurrounding();
                assert surrounding != null;
                assert !surrounding.isEmpty();
                
                Iterator<Position> it = surrounding.iterator();
                Position nextPosition = it.next();
                double closest = nextPosition.distanceTo(end);
                
                while (it.hasNext()) {
                    Position temp = it.next();
                    if (!temp.withinBounds(labyrinth.getWidth(), labyrinth.getHeight())) {
                        continue;
                    }
                    double dist = temp.distanceTo(end);
                    if (dist < closest) {
                        closest = dist;
                        nextPosition = temp;
                    }
                }                    
                path.add(nextPosition);
                traveler = nextPosition;
                
            }
            return path;
        }

    };
    
    /**
     * Find Path through field of obstructions. Preference of path qualities in descending order
     * Non-overlapping path
     * Unobstructed path
     * Path going through low priority Cells
     * Path going through high priority Cells
     * 
     * @param start Where the path should start
     * @param end End of the path
     * @return List of Positions included along the found path
     */
    public static final PathStrategy ALTERNATE = new PathStrategy() {

        @Override
        public List<Position> findPath(Labyrinth labyrinth, Position start, Position end) {
            Set<Position> traversed = new HashSet<Position>();
            traversed.add(start);
            List<Position> path = new ArrayList<Position>();
            path.add(start);
            Position current = start;
            Position next = null;
            
            while (!path.contains(end) && traversed.size() < labyrinth.getNumPositions()) {
                Set<Position> surrounding = current.getSurrounding();
                List<Position> ranked = rankPositions(labyrinth, surrounding, end);
                
                for (Position p : ranked) {
                    if (!traversed.contains(p) && p.withinBounds(labyrinth.getWidth(), labyrinth.getHeight())) {
                        next = p;
                        path.add(p);
                        traversed.add(p);
                        break;
                    }
                }
                
                if (next == null) {
                    if (current.equals(start)) {
                        return path;
                    } else {
                        path.remove(current);
                        current = path.get(path.size() - 1);
                    }
                } else {
                    current = next;
                    next = null;
                }
                
            }
            return path;
        }
        
        private List<Position> rankPositions(Labyrinth labyrinth, Collection<Position> positions, Position target) {
            Map<Position, Double> ranks = new HashMap<Position, Double>();

            for (Position position : positions) {
                boolean permeable = labyrinth.getCell(position).getCellType().isPermeable();
                double distScore = position.distanceTo(target) / labyrinth.getMaxDistance();
                double score = distScore;
                
                if (permeable) {
                    ranks.put(position, score);                    
                }
            }
            
            List<Position> sortedRanks = new ArrayList<Position>();
            sortedRanks = ranks.entrySet().stream()
                .sorted(new Comparator<Map.Entry<Position, Double>>() {

                    @Override
                    public int compare(Entry<Position, Double> o1, Entry<Position, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                    
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            
            return sortedRanks;
        }
        
    };
    
}