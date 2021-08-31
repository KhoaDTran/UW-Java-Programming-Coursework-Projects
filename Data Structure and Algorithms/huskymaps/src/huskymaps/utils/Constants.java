package huskymaps.utils;

/** A class holding constant values used throughout the project. */
public class Constants {

    /**
     * The root upper left/lower right longitudes and latitudes represent the bounding box of
     * the root tile, as the images in the img/ folder are scraped.
     * Longitude == x-axis; latitude == y-axis.
     */
    public static final double ROOT_ULLAT = 47.754097979680026;
    public static final double ROOT_ULLON = -122.6953125;
    public static final double ROOT_LRLAT = 47.51720069783939;
    public static final double ROOT_LRLON = -121.9921875;
    public static final double ROOT_LAT = (ROOT_ULLAT + ROOT_LRLAT) / 2;
    public static final double ROOT_LON = (ROOT_ULLON + ROOT_LRLON) / 2;
    public static final double ROOT_LAT_DIFF = Math.abs(ROOT_ULLAT - ROOT_LRLAT);
    public static final double ROOT_LON_DIFF = Math.abs(ROOT_LRLON - ROOT_ULLON);

    /** Size of each map tile in pixels. */
    public static final int TILE_SIZE = 256;

    public static final int MAX_DEPTH = 7;

    /**
     * The number of tiles in the x and y directions at each depth from 0 to MAX_DEPTH.
     * These numbers are different because our region is rectangular but not a square.
     */
    public static final int[] NUM_X_TILES_AT_DEPTH = {2, 4, 8, 16, 32, 64, 128, 256};
    public static final int[] NUM_Y_TILES_AT_DEPTH = {1, 2, 4,  8, 16, 32,  64, 128};

    /** The longitude spanned by a tile at each depth from 0 to MAX_DEPTH. */
    public static final double[] LON_PER_TILE = {
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[0],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[1],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[2],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[3],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[4],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[5],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[6],
            ROOT_LON_DIFF / NUM_X_TILES_AT_DEPTH[7]
    };

    /** The longitude spanned by a tile at each depth from 0 to MAX_DEPTH. */
    public static final double[] LAT_PER_TILE = {
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[0],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[1],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[2],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[3],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[4],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[5],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[6],
            ROOT_LAT_DIFF / NUM_Y_TILES_AT_DEPTH[7]
    };

    /** Radius of the Earth in miles. */
    public static final int R = 3963;

    /**
     * Scale factor at the natural origin. Prefer to use 1 instead of 0.9996 as in UTM.
     * @source https://gis.stackexchange.com/a/7298
     */
    public static final double K0 = 1.0;

    /** Error tolerance for latitudes and longitudes. */
    public static final double EPSILON = 0.000001;
    public static final int DECIMAL_PLACES = 5;

    /**
     * The OSM XML resource name. Downloaded from <a href="http://download.bbbike.org/osm/">here</a>
     * using custom region selection.
     */
    public static final String OSM_GZ_RESOURCE_NAME = "/seattle-small.osm.gz";
    public static final String PLACES_RESOURCE_NAME = "/places.json";

    /** The tile image resources are in the IMG_ROOT folder. */
    public static final String IMG_ROOT = "/tiles/";
    public static final int MIN_ZOOM_LEVEL = 10;
    public static final int[] MIN_X_TILE_AT_DEPTH = {163, 326, 652, 1304, 2608, 5216, 10432, 20864};
    public static final int[] MIN_Y_TILE_AT_DEPTH = {357, 714, 1428, 2856, 5712, 11424, 22848, 45696};

    /** HTTP failed response. */
    public static final int HALT_RESPONSE = 500;

    /** Default port for serving the application locally. */
    public static final int PORT = 8080;
}
