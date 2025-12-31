// Time Complexity : O(n) .
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : We are able to solve the problem with the help of rolling hash. As we slide the window, we calculate the incoming characters hash value and constantly
// update hash. After 10 characters, as we have to remove the characters from left, we adjust the adjust value. We maintain these hash values in a set, if a duplicate
// is found, it means the duplicate DNA, so we add to the result.

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<Character, Integer> map= new HashMap<>(); //hashmap to maintain correspondng character's values
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);
        int n = s.length();
        long hash = 0l;
        long k = (long)Math.pow(4,9);
        HashSet<Long> set = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        for(int i=0;i<n;i++){
            if(i >= 10){
                char out = s.charAt(i-10); //outgoing character
                hash = hash - k * map.get(out); //subtract the outgoing char's hash value
            }
            char in = s.charAt(i);
            hash = hash * 4 + map.get(in); //incoming char's hash value update

            if(set.contains(hash)){
                result.add(s.substring(i-9,i+1)); //duplicate DNA found
            }else{
                set.add(hash); //add to hashset
            }
        }
        return new ArrayList<>(result);
    }
}