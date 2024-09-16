package task.musicplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The `DiskPackingAlgorithm` class implements different strategies for packing music tracks into discs
 * based on their sizes and disc capacity. It provides methods for First Fit, Worst Fit, Best Fit, and First Fit Decreasing algorithms.
 * The class also includes a method for displaying the packed discs and counting the number of used discs.
 *
 * @author Nathan Li
 * @version 2023.10.8
 */
public class DiskPackingAlgorithm {

    /**
     * Packs music tracks into discs using the First Fit algorithm.
     *
     * @param tracks       The list of music tracks to be packed.
     * @param discCapacity The capacity of each disc.
     * @return A list representing the used space on each disc after packing the tracks.
     */
    public List<Double> firstFit(List<MusicTrack> tracks, double discCapacity) {
        List<Double> discs = new ArrayList<>();
        discs.add(0.0);

        for (MusicTrack track : tracks) {
            boolean placed = false;
            for (int i = 0; i < discs.size(); i++) {
                if (discs.get(i) + track.getSize() <= discCapacity) {
                    discs.set(i, discs.get(i) + track.getSize());
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                discs.add(track.getSize());
            }
        }

        return discs;
    }

    /**
     * Packs music tracks into discs using the Worst Fit algorithm.
     *
     * @param tracks       The list of music tracks to be packed.
     * @param discCapacity The capacity of each disc.
     * @return A list representing the used space on each disc after packing the tracks.
     */
    public List<Double> worstFit(List<MusicTrack> tracks, double discCapacity) {
        List<Double> discs = new ArrayList<>();
        discs.add(0.0);

        for (MusicTrack track : tracks) {
            boolean placed = false;
            int worstFitIndex = -1;
            double worstFitRemainingSpace = 0;

            for (int i = 0; i < discs.size(); i++) {
                double remainingSpace = discCapacity - discs.get(i);

                if (remainingSpace >= track.getSize() && remainingSpace > worstFitRemainingSpace) {
                    worstFitIndex = i;
                    worstFitRemainingSpace = remainingSpace;
                }
            }

            if (worstFitIndex != -1) {
                discs.set(worstFitIndex, discs.get(worstFitIndex) + track.getSize());
                placed = true;
            }

            if (!placed) {
                discs.add(track.getSize());
            }
        }

        return discs;
    }


    /**
     * Packs music tracks into discs using the Best Fit algorithm.
     *
     * @param tracks       The list of music tracks to be packed.
     * @param discCapacity The capacity of each disc.
     * @return A list representing the used space on each disc after packing the tracks.
     */
    public List<Double> bestFit(List<MusicTrack> tracks, double discCapacity) {
        List<Double> discs = new ArrayList<>();


        for (MusicTrack track : tracks) {
            boolean placed = false;
            int bestFitIndex = -1;
            double bestFitRemainingSpace = discCapacity;

            for (int i = 0; i < discs.size(); i++) {
                double remainingSpace = discCapacity - discs.get(i);
                if (remainingSpace >= track.getSize() && remainingSpace < bestFitRemainingSpace) {
                    bestFitIndex = i;
                    bestFitRemainingSpace = remainingSpace;
                }
            }

            if (bestFitIndex != -1) {
                discs.set(bestFitIndex, discs.get(bestFitIndex) + track.getSize());
                placed = true;
            }

            if (!placed) {
                discs.add(track.getSize());
            }
        }

        return discs;
    }


    /**
     * Packs music tracks into discs using the First Fit Decreasing algorithm.
     *
     * @param tracks       The list of music tracks to be packed.
     * @param discCapacity The capacity of each disc.
     * @return A list representing the used space on each disc after packing the tracks.
     */
    public List<Double> firstFitDecreasing(List<MusicTrack> tracks, double discCapacity) {
        List<Double> discs = new ArrayList<>();
        discs.add(0.0);

        Collections.sort(tracks, (track1, track2) -> Double.compare(track2.getSize(), track1.getSize()));

        for (MusicTrack track : tracks) {
            boolean placed = false;

            for (int i = 0; i < discs.size(); i++) {
                double remainingSpace = discCapacity - discs.get(i);
                if (remainingSpace >= track.getSize()) {
                    discs.set(i, discs.get(i) + track.getSize());
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                discs.add(track.getSize());
            }
        }

        return discs;
    }

    /**
     * Displays the packed discs including used space and remaining space.
     *
     * @param discs        The list representing the used space on each disc.
     * @param discCapacity The capacity of each disc.
     * @param flag         An integer flag indicating the packing algorithm used.
     * @return A formatted string displaying the packed discs and their space utilization.
     */
    public String displayDiscs(List<Double> discs, double discCapacity, int flag) {
        StringBuilder result = new StringBuilder();
        double usedSpace = 0.0;
        double remaining = 0.0;

        for (int i = 0; i < discs.size(); i++) {
            usedSpace = discs.get(i);
            remaining = discCapacity - usedSpace;
            if (remaining<0){
                return "wrong , the capacity of the CD is too small.";
            }
        }

        for (int i = 0; i < discs.size(); i++) {
            usedSpace = discs.get(i);
            remaining = discCapacity - usedSpace;
            result.append("Disc ").append(i + 1).append(":\n");
            result.append("    Used space: ").append(usedSpace).append(". Remaining space: ").append(String.format("%.2f", remaining)).append("\n");
        }
        result.append("\n");
        if (flag==1){
            return "FF\n"+result.toString();
        }
        else if(flag == 2){
            return "WF\n"+result.toString();
        }
        else if(flag == 3){
            return "BF\n"+result.toString();
        }
        else if(flag == 4){
            return "FFD\n"+result.toString();
        }
        else{return "fail";}
    }


}

