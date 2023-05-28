package Test;

import tiles.Tile;
import tiles.TileManager;
import window.Game;
import window.GamePanel;

import static org.junit.jupiter.api.Assertions.*;

class TileManagerTest {

    @org.junit.jupiter.api.Test
    void importTileImage() {
        Game game = new Game();
        GamePanel gp = new GamePanel(game);
        TileManager tm = new TileManager(gp);

        tm.importTileImage();

        Tile[] tiles = tm.tiles;
        assertEquals(10, tiles.length);

        assertEquals(false ,tiles[0].solid);
        assertNotNull(tiles[0].img);

        assertEquals(true ,tiles[1].solid);
        assertNotNull(tiles[1].img);

        assertEquals(true ,tiles[2].solid);
        assertNotNull(tiles[2].img);

        assertEquals(true ,tiles[3].solid);
        assertNotNull(tiles[3].img);

        assertEquals(true ,tiles[4].solid);
        assertNotNull(tiles[4].img);

        assertEquals(true ,tiles[5].solid);
        assertNotNull(tiles[5].img);

        assertEquals(true ,tiles[6].solid);
        assertNotNull(tiles[6].img);

        assertEquals(false ,tiles[7].solid);
        assertNotNull(tiles[7].img);
    }
}