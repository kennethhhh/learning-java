public class Lab00 {
    public static void main(String[] args) {
        //declaring and initializing some variables
        int x = 5;
        String y = "hello";
        double z = 9.8;

        //printing the variables
        System.out.println("x: " + x + " y: " + y + " z: " + z);

        //a list (make an array in java)
        int[] nums = {3,6,-1,2};
        for (int num = 0; num<4;num++){
            System.out.println(nums[num]);
        }

        //call a function
        int numFound = char_count(y, 'l');
        System.out.println("Found: " + numFound);

        //a counting for loop
        for (int i=1; i<11; i++){
            System.out.print(i+" ");
        }

        System.out.println();

    }

    public static int char_count(String s, char c){
        int count = 0;
        for (int ch = 0; ch < s.length(); ch++) {
            if (c == s.charAt(ch)) {
                count++;
            }
        }
        return count;
    }

}

