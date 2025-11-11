    import java.util.BitSet;
    class BloomFilter {
        private final BitSet bitSet;
        private final int bitSetSize;
        private final int numHashFunctions;

        public BloomFilter(int bitSetSize, int numHashFunctions) {
            this.bitSetSize = bitSetSize;
            this.numHashFunctions = numHashFunctions;
            this.bitSet = new BitSet(bitSetSize);
        }

        private int hash(String element, int seed) {
            return Math.abs((element.hashCode() + seed) % bitSetSize);
        }

        public void add(String element) {
            for (int i = 0; i < numHashFunctions; i++) {
                int hash = hash(element, i);
                bitSet.set(hash);
            }
        }

        public boolean contains(String element) {
            for (int i = 0; i < numHashFunctions; i++) {
                int hash = hash(element, i);
                if (!bitSet.get(hash)) {
                    return false;
                }
            }
            return true;
        }
    }
