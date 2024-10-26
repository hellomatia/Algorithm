class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[27];
            char[] chs = str.toCharArray();
            for (char ch : chs) {
                if (Character.isWhitespace(ch)) {
                    count[26]++;
                    continue;
                }
                count[ch - 'a']++;
            }
            String key = Arrays.toString(count);
            List<String> result = map.getOrDefault(key, new ArrayList<String>());
            result.add(str);
            map.put(key, result);
        }
        
        return map.values()
                  .stream()
                  .toList();
    }
}
