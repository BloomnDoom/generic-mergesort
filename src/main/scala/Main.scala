object Main
{
  def main(args:Array[String])=
  {
    val a=mergeSort(Array(3,2,1));
    val b=mergeSort(Vector(34,56,23,34,78,23,65));
    val c=mergeSort(Vector("hduuif","aoijdidje","sudhudfh","vcbhvbfj"));
    println(a.mkString(" "));
    println(b.mkString(" "));
    println(c.mkString(" "));
  }
  def mergeSort[T:Ordering](items:IndexedSeq[T]):IndexedSeq[T]=
  {
    if(items.length<=1) items;
    else
    {
      val (left,right)=items.splitAt(items.length/2);
      val (sortedLeft,sortedRight)=(mergeSort(left),mergeSort(right));
      var (leftIdx,rightIdx)=(0,0);
      val output=IndexedSeq.newBuilder[T];
      while(leftIdx<sortedLeft.length||rightIdx<sortedRight.length)
      {
        val takeLeft=(leftIdx<sortedLeft.length,rightIdx<sortedRight.length) match
          {
            case(true,false)=>true;
            case(false,true)=>false;
            case(true,true)=>Ordering[T].lt(sortedLeft(leftIdx),sortedRight(rightIdx));
          }
        if(takeLeft)
        {
          output+=sortedLeft(leftIdx);
          leftIdx+=1;
        }
        else
        {
          output+=sortedRight(rightIdx);
          rightIdx+=1;
        }
      }
      output.result();
    }
  }
}
