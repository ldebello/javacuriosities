package ar.com.javacuriosities.helper;


public enum MemoryUnit {
    BYTES {
        @Override
        public long toBytes(long size) {
            return size;
        }

        @Override
        public long toMegaBytes(long size) {
            return size / C2;
        }
    },
    MEGABYTES {
        @Override
        public long toBytes(long size) {
            return size * C2;
        }

        @Override
        public long toMegaBytes(long size) {
            return size;
        }
    };

    static final long C0 = 1L;
    static final long C1 = C0 * 1024L;
    static final long C2 = C1 * 1024L;

    public long toBytes(long size) {
        throw new AbstractMethodError();
    }

    public long toMegaBytes(long size) {
        throw new AbstractMethodError();
    }
}
