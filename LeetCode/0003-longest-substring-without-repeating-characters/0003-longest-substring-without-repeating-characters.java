class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        for (int length = s.length() < 127 ? s.length() : 127; length >= 0; length--) {
            int[] alphaCount = getAlphaCount(length, s);
            int uniqueCount = getUniqueCount(alphaCount);

            if (uniqueCount == length) {
                return length;
            }

            for (int i = 0; i < s.length() - length; i++) {
                int start = s.charAt(i); 
                int end = s.charAt(i + length);
                
                alphaCount[start]--;
                if (alphaCount[start] == 1) {
                    uniqueCount++;
                } else if (alphaCount[start] == 0) {
                    uniqueCount--;
                }

                alphaCount[end]++;
                if (alphaCount[end] == 1) {
                    uniqueCount++;
                } else if (alphaCount[end] == 2) {
                    uniqueCount--;
                }

                if (uniqueCount == length) {
                    return length;
                }
            }
        }
        return 0;
    }

    public int[] getAlphaCount(int length, String s) {
        int[] alphaCount = new int[128];
        for (int i = 0; i < length; i++) {
            alphaCount[s.charAt(i)]++;
        }
        return alphaCount;
    }

    public int getUniqueCount(int[] alphaCount) {
        int count = 0;
        for (int num : alphaCount) {
            if (num == 1) {
                count++;
            };
        }
        return count;
    }
}
