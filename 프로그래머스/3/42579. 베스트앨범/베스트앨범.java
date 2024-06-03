
import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genrePlayMap = new HashMap<>();
        Map<String, List<Music>> genreMusicsMap = new HashMap<>();

        int numOfMusics = plays.length;
        for (int i = 0; i < numOfMusics; i++) {
            // genre
            Genre genre = genrePlayMap.get(genres[i]);
            if (genre == null) {
                genrePlayMap.put(genres[i], new Genre(genres[i]));
                genre = genrePlayMap.get(genres[i]);
            }
            genre.addPlay(plays[i]);

            // music
            List<Music> musics = genreMusicsMap.get(genres[i]);
            if (musics == null) {
                genreMusicsMap.put(genres[i], new ArrayList<>());
                musics = genreMusicsMap.get(genres[i]);
            }
            musics.add(new Music(i, plays[i]));
        }

        List<Genre> genreList = genrePlayMap.values()
                .stream()
                .collect(Collectors.toList());
        Collections.sort(genreList);

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < genreList.size(); i++) {
            Genre genre = genreList.get(i);
            List<Music> musics = genreMusicsMap.get(genre.getName());
            Collections.sort(musics);
            
            for (int j = 0; j < Math.min(musics.size(), 2); j++) {
                answer.add(musics.get(j).getNum());
            }
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static class Genre implements Comparable<Genre> {
        private final String name;
        private long totalPlay;

        public Genre(String name) {
            this.name = name;
        }

        public void addPlay(int play) {
            totalPlay += play;
        }

        public String getName() {
            return name;
        }

        public long getTotalPlay() {
            return totalPlay;
        }

        @Override
        public int compareTo(Genre o) {
            return Long.compare(o.getTotalPlay(), getTotalPlay());
        }
    }

    private static class Music implements Comparable<Music> {
        private final int num;
        private final int play;

        public Music(int num, int play) {
            this.num = num;
            this.play = play;
        }

        public int getNum() {
            return num;
        }

        public int getPlay() {
            return play;
        }

        @Override
        public int compareTo(Music o) {
            if (o.getPlay() == play) {
                return num - o.getNum();
            }
            return o.getPlay() - play;
        }
    }
}
