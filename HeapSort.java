public class HeapSort{

    void sort(int[] arr) {
        for(int i = arr.length; i > 0; i--) {
            max_heapify(arr, i);
            int temp = arr[0];
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
    }

    int find(int[] arr, int n) {
        for(int i = arr.length; i > arr.length - n; i--){
            max_heapify(arr, i);
            int temp = arr[0];
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
        return arr[arr.length - n];
    }

    static void max_heapify(int[] arr, int limit) {
        if(arr.length <= 0 || arr.length < limit) return;
        int parentIdx = limit / 2;
        for(; parentIdx >= 0; parentIdx--) {
            if(parentIdx * 2 >= limit) {
                continue;
            }
            int left = parentIdx * 2;
            int right = (left + 1) >= limit ? left : (left + 1);
            int maxChildId = arr[left] >= arr[right] ? left : right;
            if(arr[maxChildId] > arr[parentIdx]) {
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{3,5,3,0,8,6,1,5,8,6,2,4,9,4,7,0,1,8,9,7,3,1,2,5,9,7,4,0,2,6};
        int[] arr = new int[]{3,2,6,5,1,8,7,4};
        HeapSort h = new HeapSort();
        // h.sort(arr);
        // System.out.println(Arrays.toString(arr));
        System.out.println(h.find(arr, 3));
    }
}
