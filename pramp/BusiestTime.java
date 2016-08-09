public class BusiestTime {
  public static enum Type{
    ENTER,
    EXIT;
  }

  public static class EntryData{
    public final long time;
    public final int count;
    public final Type type;
    
    public EntryData(long time, int count, Type type){
      this.time = time;
      this.count = count;
      this.type = type;
    }
  }
  
  public EntryData[] sort(EntryData[] data){
    return data;
  }

  public long[] findBusiestTime(EntryData[] entryData) throws Exception {
    entryData = sort(entryData);   
    // current counters
    long currentStart = entryData[0].time;
    long currentEnd = entryData[0].time;
    long currentPeople = entryData[0].count;
    // max counters
    long maxStart = entryData[0].time;
    long maxEnd = entryData[0].time;
    long maxPeople = entryData[0].count;

    for(int idx = 0; idx < entryData.length; idx++){
      currentEnd = entryData[idx].time;
      if(entryData[idx].type == Type.ENTER)
        currentPeople += entryData[idx].count;
      else
        currentPeople -= entryData[idx].count;

      while(idx < entryData.length-1 &&
            entryData[idx+1].time == entryData[idx].time){
        currentPeople += entryData[idx].count;
        currentEnd = entryData[idx].time;
        currentStart = entryData[idx].time;
      }

      if(currentPeople > maxPeople){
        maxPeople = currentPeople;
        maxEnd = currentEnd;
        maxStart = currentStart; 
      }
    }
    return new long[]{maxStart, maxEnd};
  }

  public void print(long[] result){
    System.out.println(result[0] + "<-->"+ result[1]);
  }

  public static void main(String[] args){
    BusiestTime busiestTime = new BusiestTime();
    EntryData[] entryData = new EntryData[]{new EntryData(1L, 4, Type.ENTER), new EntryData(2L, 2, Type.EXIT),
                                            new EntryData(3L, 6, Type.ENTER), new EntryData(4L, 2, Type.ENTER)};
    try{
      busiestTime.print(busiestTime.findBusiestTime(entryData));  
    } catch(Exception exp){
      System.out.println(exp);
    }
  }
}