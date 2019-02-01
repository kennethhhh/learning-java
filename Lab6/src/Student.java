import java.util.List;
import java.util.Objects;

class Student
{
    private final String surname;
    private final String givenName;
    private final int age;
    private final List<CourseSection> currentCourses;

    public Student(final String surname, final String givenName, final int age,
        final List<CourseSection> currentCourses)
    {
        this.surname = surname;
        this.givenName = givenName;
        this.age = age;
        this.currentCourses = currentCourses;
    }

    public boolean equals(Object other){
        if (other!=null) {
            boolean surnameEQ = Objects.equals(this.surname,((Student) other).surname);
            boolean givenNameEQ = this.givenName.equals(((Student) other).givenName);
            boolean ageEQ = this.age == ((Student) other).age;
            boolean currentCoursesEQ= Objects.equals(this.currentCourses, ((Student)other).currentCourses);
            return surnameEQ && givenNameEQ && ageEQ && currentCoursesEQ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (Objects.hash(surname, givenName, age, currentCourses));
    }
}
