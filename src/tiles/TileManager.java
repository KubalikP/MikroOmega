package tiles;

import window.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];
    private int tileSize = 50;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.getScreenCol()][gp.getScreenRow()];
        importTileImage();
        loadMap();
    }

    //method for importing tile images
    public void importTileImage() {
        try{
            tiles[0] = new Tile();
            tiles[0].img = ImageIO.read(getClass().getResourceAsStream("/TileEmpty.png"));
            tiles[0].solid = false;

            tiles[1] = new Tile();
            tiles[1].img = ImageIO.read(getClass().getResourceAsStream("/TileGrass.png"));
            tiles[1].solid = true;

            tiles[2] = new Tile();
            tiles[2].img = ImageIO.read(getClass().getResourceAsStream("/TileDirt.png"));
            tiles[2].solid = true;

            tiles[3] = new Tile();
            tiles[3].img = ImageIO.read(getClass().getResourceAsStream("/TilePlatformLeft.png"));
            tiles[3].solid = true;

            tiles[4] = new Tile();
            tiles[4].img = ImageIO.read(getClass().getResourceAsStream("/TilePlatformMiddle.png"));
            tiles[4].solid = true;

            tiles[5] = new Tile();
            tiles[5].img = ImageIO.read(getClass().getResourceAsStream("/TilePlatformRight.png"));
            tiles[5].solid = true;

            tiles[6] = new Tile();
            tiles[6].img = ImageIO.read(getClass().getResourceAsStream("/TileCrate.png"));
            tiles[6].solid = true;

            tiles[7] = new Tile();
            tiles[7].img = ImageIO.read(getClass().getResourceAsStream("/BG.png"));
            tiles[7].solid = false;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.getScreenCol() && row < gp.getScreenRow()){
            int tileNum = mapTileNum[col][row];
            g.drawImage(tiles[tileNum].img, x, y, tileSize, tileSize, null);
            col++;
            x += tileSize;

            if(col == gp.getScreenCol()){
                col = 0;
                x = 0;
                row++;
                y += tileSize;
            }
        }
    }

    //method for loading map
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/Map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.getScreenCol() && row < gp.getScreenRow()) {
                String line = br.readLine();

                if(line == null){
                    break;
                }

                String numbers[] = line.split(" ");

                while (col < gp.getScreenCol() && col < numbers.length) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.getScreenCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
