public class Main {
    public static void main(String[] args) {
        XYZextractor xyz = new XYZextractor();

        /**
         * To calculate the desirable number of geometries for extraction
         * it is necessary divide a total number of geoms (T) minus number of
         * skipped geoms (S) by the the number of desired geoms (D).
         * It makes a step size
         *
         * step = (T - S) / D
         *
         */
        xyz.doextraction("coors.xyz", 980, 4000, 0);
    }
}
