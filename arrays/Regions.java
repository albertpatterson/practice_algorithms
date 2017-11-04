package arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by apatters on 11/3/2017.
 */
public class Regions {

    public int[] getReqionSizes(boolean[][] map){

        ArrayList<Integer> regionSizes = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        for(int row = 0; row<map.length; row++){
            for(int col=0; col<map[0].length; col++){
                regionCount(map, regionSizes, visited, row, col, -1);
            }
        }


        int[] sizes = new int[regionSizes.size()];
        int idx=0;
        for(Integer size : regionSizes){
            sizes[idx++]=size;
        }
        return sizes;
    }

    private void regionCount(boolean[][] map, ArrayList<Integer> regionSizes, boolean[][] visited, int row, int col, int curRegion){

        if(visited[row][col]){
            return;
        }

        visited[row][col]=true;

        boolean isPart = map[row][col]==true;

        if(isPart){
            if(curRegion>-1){
                regionSizes.set(curRegion, regionSizes.get(curRegion)+1);
            }else{
                curRegion = regionSizes.size();
                regionSizes.add(curRegion, 1);
            }

            int minRow = Math.max(row-1, 0);
            int maxRow = Math.min(row+1, map.length-1);

            int minCol = Math.max(col-1, 0);
            int maxCol = Math.min(col+1, map[0].length-1);

            for(int curRow = minRow; curRow<=maxRow; curRow++){
                for(int curCol=minCol; curCol<=maxCol; curCol++){
                    if(!(curRow == row && curCol == col)){
                        regionCount(map, regionSizes, visited, curRow, curCol, curRegion);
                    }
                }
            }

        }
    }


//    private int checkRegion(boolean[][] map, ArrayList<Integer> regionSizes, boolean[][] visited, int row, int col,int curRegion){
//        visited[row][col]=true;
//        boolean isPart = map[row][col]==true;
//        if(isPart){
//            if(curRegion>0){
//                regionSizes.add(curRegion, regionSizes.get(curRegion)+1);
//            }else{
//                curRegion = regionSizes.size()+1;
//                regionSizes.add(curRegion, 1);
//            }
//        }else{
//            curRegion = 0;
//        }
//        return curRegion;
//    }

    private void regionSearch(boolean[][] map, ArrayList<Integer> regionSizes, boolean[][] visited, int row, int col, int curRegion){

        if(visited[row][col]){
            return;
        }

        visited[row][col]=true;

        boolean isPart = map[row][col]==true;

        System.out.println(row + " " +col + " " + isPart);

        if(isPart){
            if(curRegion>-1){
                regionSizes.set(curRegion, regionSizes.get(curRegion)+1);
            }else{
                curRegion = regionSizes.size();
                regionSizes.add(curRegion, 1);
            }
        }else{
            curRegion = -1;
        }

        LinkedList<RowCol> neighbors = new LinkedList<>();

        int minRow = Math.max(row-1, 0);
        int maxRow = Math.min(row+1, map.length-1);

        int minCol = Math.max(col-1, 0);
        int maxCol = Math.min(col+1, map[0].length-1);

        for(int curRow = minRow; curRow<=maxRow; curRow++){
            for(int curCol=minCol; curCol<=maxCol; curCol++){
                if(curRow == row && curCol == col){
                    continue;
                }

                if(map[curRow][curCol]==true){
                    neighbors.addFirst(new RowCol(curRow, curCol));
                }else{
                    neighbors.addLast(new RowCol(curRow, curCol));
                }
            }
        }

        for(RowCol neighbor : neighbors){
            regionSearch(map, regionSizes, visited, neighbor.row, neighbor.col, curRegion);
        }
    }
}

class RowCol{
    int row;
    int col;
    public RowCol(int r, int c){
        row=r;
        col=c;
    }
}