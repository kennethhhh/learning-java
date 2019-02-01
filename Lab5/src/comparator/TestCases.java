package comparator;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
    private static final Song[] songs = new Song[] {
        new Song("Decemberists", "The Mariner's Revenge Song", 2005),
        new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
        new Song("Avett Brothers", "Talk on Indolence", 2006),
        new Song("Gerry Rafferty", "Baker Street", 1998),
        new Song("City and Colour", "Sleeping Sickness", 2007),
        new Song("Foo Fighters", "Baker Street", 1997),
        new Song("Queen", "Bohemian Rhapsody", 1975),
        new Song("Gerry Rafferty", "Baker Street", 1978)
      };

    @Test
    public void testArtistComparator()
    {
        Song[] songsToCompare = new Song[] { songs[6], songs[7] };
        Arrays.sort(songsToCompare, new ArtistComparator());
        Song[] sortedSongs = new Song[] { songs[7], songs[6] };
        assertArrayEquals(songsToCompare, sortedSongs);

        Song[] songsToCompare1 = new Song[] { songs[7], songs[0] };
        Arrays.sort(songsToCompare1, new ArtistComparator());
        Song[] sortedSongs1 = new Song[] { songs[0], songs[7] };
        assertEquals(songsToCompare1, sortedSongs1);

        Song[] songsToCompare2 = new Song[] { songs[3], songs[4] };
        Arrays.sort(songsToCompare2, new ArtistComparator());
        Song[] sortedSongs2 = new Song[] { songs[4], songs[3] };
        assertEquals(songsToCompare2, sortedSongs2);
    }

    @Test
    public void testLambdaTitleComparator()
    {
        Comparator<Song> comp = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());

        Song[] songsToCompare = new Song[]{songs[2], songs[7]};
        Arrays.sort(songsToCompare,comp);
        Song[] sortedSongs = new Song[]{songs[7], songs[2]};
        assertEquals(songsToCompare,sortedSongs);

        Song[] songsToCompare1 = new Song[]{songs[0], songs[1]};
        Arrays.sort(songsToCompare1,comp);
        Song[] sortedSongs1 = new Song[]{songs[1], songs[0]};
        assertEquals(songsToCompare1,sortedSongs1);

        Song[] songsToCompare2 = new Song[]{songs[3], songs[7]};
        Arrays.sort(songsToCompare2,comp);
        Song[] sortedSongs2 = new Song[]{songs[3], songs[7]};
        assertEquals(songsToCompare2,sortedSongs2);


        Song[] songsToCompare3 = new Song[]{songs[1], songs[6]};
        Arrays.sort(songsToCompare3,comp);
        Song[] sortedSongs3 = new Song[]{songs[6], songs[1]};
        assertEquals(songsToCompare3,sortedSongs3);
    }

    @Test
    public void testYearExtractorComparator()
    {
        Comparator<Song> comp = Comparator.comparing(Song::getYear, Comparator.reverseOrder());

        Song[] songsToComp = new Song[]{songs[1],songs[2]};
        Arrays.sort(songsToComp,comp);
        Song[] sortedSongs = new Song[]{songs[2],songs[1]};
        assertEquals(songsToComp,sortedSongs);

        Song[] songsToComp1 = new Song[]{songs[2],songs[7]};
        Arrays.sort(songsToComp1,comp);
        Song[] sortedSongs1 = new Song[]{songs[2],songs[7]};
        assertEquals(songsToComp1,sortedSongs1);

        Song[] songsToComp2 = new Song[]{songs[5],songs[0]};
        Arrays.sort(songsToComp2,comp);
        Song[] sortedSongs2 = new Song[]{songs[0],songs[5]};
        assertEquals(songsToComp2,sortedSongs2);
    }

    @Test
    public void testComposedComparator()
    {
        Comparator<Song> compYear = Comparator.comparing(Song::getYear);
        Comparator<Song> compTitle= (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());

        // Test1
        Comparator<Song> artistThenYear = new ComposedComparator(new ArtistComparator(), compYear);
        Song[] songList1 = new Song[] {songs[3], songs[7]};
        Arrays.sort(songList1, artistThenYear);
        Song[] expected1 = new Song[] {songs[7], songs[3]};
        assertEquals(expected1, songList1);

        //Test2
        Comparator<Song> titleThenArtist = new ComposedComparator(compTitle, new ArtistComparator());
        Song[] songList2 = new Song[] {songs[3], songs[5]};
        Arrays.sort(songList2, titleThenArtist);
        Song[] expected2 = new Song[] {songs[5], songs[3]};
        assertEquals(expected2, songList2);

        //Test3
        Comparator<Song> yearThenTitle = new ComposedComparator(compYear, compTitle);
        Song[] songList3 = new Song[] {songs[1], songs[0]};
        Arrays.sort(songList3, yearThenTitle);
        Song[] expected3 = new Song[] {songs[1], songs[0]};
        assertEquals(expected3, songList3);
    }

    @Test
    public void testThenComparing()
    {
        Comparator<Song> comp = Comparator.comparing(Song::getTitle).thenComparing(new ArtistComparator());

        Song[] list1 = new Song[] {songs[0], songs[1]};
        Arrays.sort(list1, comp);
        Song[] list2 = new Song[] {songs[1], songs[0]};
        assertEquals(list2, list1);

        Song[] list3 = new Song[] {songs[3], songs[5]};
        Arrays.sort(list1, comp);
        Song[] list4 = new Song[] {songs[5], songs[3]};
        assertEquals(list2, list1);
    }

    @Test
    public void runSort()
    {
        List<Song> songList = new ArrayList<Song>(Arrays.asList(songs));
        List<Song> expectedList = Arrays.asList(
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Gerry Rafferty", "Baker Street", 1998),
            new Song("Queen", "Bohemian Rhapsody", 1975),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
            );

        Comparator<Song> comp = Comparator.comparing(Song::getArtist,(s1,s2)->s1.compareTo(s2))
                .thenComparing(Song::getTitle, (s1,s2)->s1.compareTo(s2))
                .thenComparing(Song::getYear, (s1,s2)->s1.compareTo(s2));

        songList.sort(comp
         // pass comparator here
        );

        assertEquals(songList, expectedList);
    }
}
