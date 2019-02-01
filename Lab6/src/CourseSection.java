import java.time.LocalTime;

class CourseSection
{
    private final String prefix;
    private final String number;
    private final int enrollment;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public CourseSection(final String prefix, final String number,
        final int enrollment, final LocalTime startTime, final LocalTime endTime)
    {
        this.prefix = prefix;
        this.number = number;
        this.enrollment = enrollment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean equals(Object other){
        if (other!=null){
            if(this.getClass()==other.getClass()){
                boolean prefixEQ= this.prefix.equals(((CourseSection)other).prefix);
                boolean numberEQ= this.number.equals(((CourseSection)other).number);
                boolean enrollmentEQ= this.enrollment==((CourseSection)other).enrollment;
                boolean startEQ= this.startTime.equals(((CourseSection)other).startTime);
                boolean endEQ= this.endTime.equals(((CourseSection)other).endTime);
                return (prefixEQ && numberEQ && enrollmentEQ && startEQ && endEQ);
            }
        }
        return false;
    }

    public int hashCode(){
        int pHash=0;
        int nHash=0;
        int sHash=0;
        int eHash=0;
        if (this.prefix!=null){
            pHash=this.prefix.hashCode();
        }
        if (this.number!=null){
            nHash=this.number.hashCode();
        }
        if (this.startTime!=null){
            sHash=this.startTime.hashCode();
        }
        if (this.endTime!=null){
            eHash=this.endTime.hashCode();
        }
        return (pHash+nHash+this.enrollment+sHash+eHash);
    }
   // additional likely methods not defined since they are not needed for testing
}
