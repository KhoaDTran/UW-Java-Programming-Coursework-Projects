package huskymaps.rastering;

import huskymaps.graph.Coordinate;

import static huskymaps.utils.Constants.ROOT_ULLAT;
import static huskymaps.utils.Constants.ROOT_ULLON;
import static huskymaps.utils.Constants.LON_PER_TILE;
import static huskymaps.utils.Constants.LAT_PER_TILE;

/**
 * @see Rasterer
 */
public class DefaultRasterer implements Rasterer {

    public TileGrid rasterizeMap(Coordinate ul, Coordinate lr, int depth) {
        int lonMin = (int) Math.floor((ul.lon() - ROOT_ULLON) / LON_PER_TILE[depth]);
        int lonMax = (int) Math.floor((lr.lon() - ROOT_ULLON) / LON_PER_TILE[depth]);
        int latMin = (int) Math.floor((ROOT_ULLAT - ul.lat()) / LAT_PER_TILE[depth]);
        int latMax = (int) Math.floor((ROOT_ULLAT - lr.lat()) / LAT_PER_TILE[depth]);
        if (lonMin < 0) {
            lonMin = 0;
        }
        if (latMin < 0) {
            latMin = 0;
        }

        int maxDepth = (int) Math.pow(2.0, (double) depth);
        if (latMax > maxDepth - 1) {
            latMax = maxDepth - 1;
        }
        if (lonMax > maxDepth * 2 - 1) {
            lonMax = maxDepth * 2 - 1;
        }
        Tile[][] grid = null;
        if (latMax < 0 || latMin > maxDepth - 1 || lonMin > maxDepth * 2 - 1 || lonMax < 0) {
            grid[0][0] = new Tile(depth, 0, 0);
        }
        else {
            grid = new Tile[latMax - latMin + 1][lonMax - lonMin + 1];
            for (int lat = latMin; lat <= latMax; lat++) {
                for (int lon = lonMin; lon <= lonMax; lon++) {
                    grid[lat - latMin][lon - lonMin] = new Tile(depth, lon, lat);
                }
            }
        }
        return new TileGrid(grid);
    }
}
