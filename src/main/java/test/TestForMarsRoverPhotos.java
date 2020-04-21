package test;

import Parsing.Parsing;
import connection.MarsRoverPhotos;
import data.APODObject;
import data.marsRoverPhotos.Photo;
import data.marsRoverPhotos.PhotoMars;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import testData.MarsRoverPhotosTestData;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class TestForMarsRoverPhotos {

    public static Collection<Object[]> inputData() {
        return Arrays.asList(new Object[][]{
                {MarsRoverPhotosTestData.sol1},
                {MarsRoverPhotosTestData.sol2},
                {MarsRoverPhotosTestData.sol3},
                {MarsRoverPhotosTestData.sol4}
        });
    }

    @Test
    @Parameters(method = "inputData")
    public void testConvertFromIntToArrayInt(Integer sol) throws IOException {
        MarsRoverPhotos marsRoverPhotos = new MarsRoverPhotos();
        String response = marsRoverPhotos.run(sol.toString());
        Parsing pars = new Parsing(PhotoMars.class, response);
        PhotoMars object = (PhotoMars)pars.parseDataFromJsonFailProperties();
        List<Photo> photos = object.getPhotos();
        for (Photo photo : photos){
            Assert.assertEquals(sol, photo.getSol());
        }
    }
}