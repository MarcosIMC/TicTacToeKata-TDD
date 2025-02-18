import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class GridShould {
	static Grid grid;

	@BeforeEach
	void setUp() {
		grid = new Grid();
	}

	@Test
	void get_3_for_width() {
		assertEquals(3, grid.getWidth());
	}

	@Test
	void get_3_for_height() {
		assertEquals(3, grid.getHeight());
	}

	@Test
	void get_a_position_of_the_grid() {
		int coordinateRandomX = (int)(Math.random() * grid.getWidth());
		int coordinateRandomY = (int)(Math.random() * grid.getHeight());

		assertNull(grid.getPieceOf(coordinateRandomX, coordinateRandomY));
	}

	@Test
	void set_a_piece_on_the_grid() {
		GamePieces randomPiece = getRandomGamePiece();
		grid.setAPiece(randomPiece, 0, 1);

		assertEquals(grid.getPieceOf(0, 1), randomPiece);
	}

	@Test
	void return_true_if_have_a_piece_on_the_coordinates() {
		GamePieces randomPiece = getRandomGamePiece();
		grid.setAPiece(randomPiece, 0, 1);

		assertTrue(grid.haveAPieceIn(0, 1));
	}

	@Test
	void return_false_if_havent_a_piece_on_the_coordinates() {
		assertFalse(grid.haveAPieceIn(0, 1));
	}

	@Test
	void return_false_if_the_grid_are_full() {
		setAllPieces();
		assertFalse(grid.haveFreeSpace());
	}

	@Test
	void return_true_if_any_row_have_the_same_piece() {
		setAllPieces();
		assertTrue(grid.anyRowHaveTheSamePiece());
	}

	@Test
	void return_true_if_any_column_have_the_same_piece() {
		setAllPieces();
		assertTrue(grid.anyColumnHaveTheSamePiece());
	}

	@Test
	void return_true_if_any_diagonal_have_the_same_piece() {
		setAllPieces();
		assertTrue(grid.anyDiagonalHaveTheSamePiece());
	}

	private void setAllPieces() {
		for (int x = 0; x < grid.getHeight(); x++) {
			for (int y = 0; y < grid.getWidth(); y++) {
				grid.setAPiece(GamePieces.X, x, y);
			}
		}
	}

	public static GamePieces getRandomGamePiece() {
		GamePieces[] values = GamePieces.values(); // Obtiene todos los valores del enum
		int randomIndex = ThreadLocalRandom.current().nextInt(values.length); // Genera un índice aleatorio
		return values[randomIndex]; // Retorna el valor aleatorio
	}
}
