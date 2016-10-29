import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xuanwang on 10/28/16.
 */
public class Intervals {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e;}
    }

    public static int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b)->(a.start-b.start));
        Queue<Interval> q = new PriorityQueue<>((a, b) -> (a.end - b.end));
        q.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            Interval interval = q.poll();
            if(intervals[i].start >= interval.end){
                interval.end = intervals[i].end;
            }else{
                q.offer(intervals[i]);
            }
            q.offer(interval);
        }
        return q.size();
    }


}
