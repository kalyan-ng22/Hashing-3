// Time Complexity : O(n * k) . n is the number of users and k is the number of songs.
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : Yes
// Approach : We first create a hashmap to capture genre of each song. Then we iterate over each user, get the list of songs and capture the genre of that
// song through the newly created hashmap. Now we create one more frequency hashmap to store the frequency of each genre for each user and capture the max
// frequency as well. Now we iterate over frequency map and assign the most frequent genre to the user in the result.

public class Main {
    public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        HashMap<String,String> songToGenre = new HashMap<>(); //hashMap to store song as key and genre as value
        for(String genre : genreMap.keySet()){
            List<String> songsList = genreMap.get(genre);
            for(String song : songsList){
                songToGenre.put(song, genre);
            }
        }

        HashMap<String, List<String>> result = new HashMap<>();
        for(String user : userMap.keySet()){
            result.put(user, new ArrayList<>());
            int max = Integer.MIN_VALUE;
            List<String> songsList = userMap.get(user); //songs list of a user
            HashMap<String, Integer> freqMap = new HashMap<>(); //hashmap to store genre as key and it's frequency as value
            for(String song : songsList){
                String genre = songToGenre.get(song); //genre of each song
                freqMap.put(genre, freqMap.getOrDefault(genre, 0) + 1); //add to frequency map
                max = Math.max(max, freqMap.get(genre)); //capture maximum genre
            }

            for(String genre : freqMap.keySet()){ //iterate over frequency map
                if(freqMap.get(genre) == max){ //max listened genre is encountered
                    result.get(user).add(genre); //add to result
                }
            }

        }
        return result;
    }


    public static void main(String[] args) {
        HashMap<String, List<String>> userSongs = new HashMap<>();

        userSongs.put("David", Arrays.asList(new String[]{"song1", "song2", "song3", "song4", "song8"}));

        userSongs.put("Emma", Arrays.asList(new String[]{"song5", "song6", "song7"}));

        HashMap<String, List<String>> songGenres = new HashMap<>();

        songGenres.put("Rock", Arrays.asList(new String[]{"song1", "song3"}));

        songGenres.put("Dubstep", Arrays.asList(new String[]{"song7"}));

        songGenres.put("Techno", Arrays.asList(new String[]{"song2", "song4"}));

        songGenres.put("Pop", Arrays.asList(new String[]{"song5", "song6"}));

        songGenres.put("Jazz", Arrays.asList(new String[]{"song8", "song9"}));

        Map<String, List<String>> res = favoritegenre(userSongs, songGenres);

        System.out.println(res);
    }
}