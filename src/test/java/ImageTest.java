import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {

    @Test
    void test() throws IOException {
//        BufferedImage image = ImageIO.read(new File("D:/study/project/tank-master/tank/src/images/GoodTank1.png"));
//        assertNotNull(image);
        BufferedImage image1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/e1.gif"));
        //ImageTest.Class
        assertNotNull(image1);
    }
}
