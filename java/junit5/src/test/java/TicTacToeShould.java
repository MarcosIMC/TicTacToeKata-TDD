import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeShould {
	static TicTacToe game;

	@BeforeAll
	static void setUp() {
		game = new TicTacToe();
		game.start();
	}

	@Test
	void start_a_game_with_two_players_with_diferents_pieces() {
		Player[] players = game.getPlayers();

		assertNotSame(players[0].piece, players[1].piece);
	}

	@Test
	void start_a_new_world_to_play() {
		Grid grid = game.getGrid();

		assertTrue(grid.getHeight() == 3 && grid.getWidth() == 3);
	}

	@Test
	void put_a_piece_in_a_field_on_the_grid() {
		Player[] players = game.getPlayers();
		int coordinateX = 0;
		int coordinateY = 1;

		game.setAPiece(players[0], coordinateX, coordinateY);

		assertTrue(game.haveAPieceIn(coordinateX, coordinateY));
	}

	@Test
	void cant_put_a_piece_in_a_field_that_are_busy() {
		Player[] players = game.getPlayers();
		int coordinateX = 0;
		int coordinateY = 1;
		Grid grid = game.getGrid();

		if (!grid.haveAPieceIn(coordinateX, coordinateY)) {
			game.setAPiece(players[0], coordinateX, coordinateY);
		}

		assertTrue(game.haveAPieceIn(coordinateX, coordinateY));
	}

	@Test
	void end_a_game_when_all_fields_are_busy() {
		setAllPieces();

		assertTrue(game.isFinished());
	}

	@Test
	void not_end_a_game_when_have_free_space_on_the_grid() {
		Grid grid = game.getGrid();
		grid.setAPiece(GamePieces.X, 0, 0);

		assertFalse(game.isFinished());
	}

	@Test
	void when_start_a_new_game_one_random_player_start() {
		Player[] players = game.getPlayers();

		assertNotEquals(players[0].getTurn(), players[1].getTurn());
	}

	@Test
	void game_is_over_when_one_player_take_all_fields_of_a_row() {
		setAllPieces();
		assertTrue(game.anyPlayerWinInARow());
	}

	@Test
	void game_is_over_when_one_player_take_a_field_of_a_column() {
		setAllPieces();
		assertTrue(game.anyPlayerWinInAColumn());
	}

	@Test
	void game_is_over_when_one_player_take_a_diagonal() {
		setAllPieces();
		assertTrue(game.anyPlayerWinInADiagonal());
	}

	private void setAllPieces() {
		Grid grid = game.getGrid();

		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				grid.setAPiece(GamePieces.X, x, y);
			}
		}
	}
}
