package leetcode.twopointers;

public class FindTheIndex {
    public int strStr(String haystack, String needle) {
        for(int i = 0; i < haystack.length(); i++) {
            boolean notIn = false;
            for(int j = 0; j < needle.length(); j++) {
                if(i + j >= haystack.length()){
                    notIn = true;
                    break;
                }

                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    notIn = true;
                    break;
                }
            }

            if (!notIn) {
                return i;
            }
        }

        return -1;
    }


}
