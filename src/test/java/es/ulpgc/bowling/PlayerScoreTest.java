package es.ulpgc.bowling;

import org.junit.Before;
import org.junit.Test;

public class PlayerScoreTest {

    private PlayerScore playerScore;

    @Before
    public void setUp() throws Exception {
        playerScore = new PlayerScore("mario");
    }

    @Test
    public void given_new_player_score_frames_should_be_zero() {
        assert(playerScore.frames().size()==0);
    }

    @Test
    public void given_one_roll_frames_should_be_one() {
        playerScore.roll(4);
        assert(playerScore.frames().size()==1);
        assert(playerScore.frames().get(0).score()==null);
    }

    @Test
    public void given_two_rolls_frame_score_should_be_the_sum_of_them() {
        playerScore.roll(4).roll(3);
        assert(playerScore.frames().size()==1);
        assert(playerScore.frames().get(0).score()==7);
    }

    @Test
    public void given_three_rolls_frames_should_be_two() {
        playerScore.roll(4).roll(3).roll(8);
        assert(playerScore.frames().size()==2);
        assert(playerScore.frames().get(0).score()==7);
        assert(playerScore.frames().get(1).score()==null);
    }

    @Test
    public void given_a_spare_frame_score_should_be_null() {
        playerScore.roll(4).roll(6);
        assert(playerScore.frames().size()==1);
        assert(playerScore.frames().get(0).score()==null);
    }

    @Test
    public void given_a_spare_and_a_roll_frame_score_should_be_the_sum_of_them() {
        playerScore.roll(4).roll(6).roll(5);
        assert(playerScore.frames().size()==2);
        assert(playerScore.frames().get(0).score()==15);
        assert(playerScore.frames().get(1).score()==null);
    }

    @Test
    public void given_a_strike_and_a_roll_frame_score_should_be_null() {
        playerScore.roll(10).roll(9);
        assert(playerScore.frames().size()==2);
        assert(playerScore.frames().get(0).score()==null);
        assert(playerScore.frames().get(1).score()==null);
    }

    @Test
    public void given_a_strike_and_a_spare_frame_score_should_be_20() {
        playerScore.roll(10).roll(9).roll(1);
        assert(playerScore.frames().size()==2);
        assert(playerScore.frames().get(0).score()==20);
        assert(playerScore.frames().get(1).score()==null);
    }

     @Test
     public void given_a_perfect_game_total_score_should_be_300() {
         playerScore.roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10);
         assert(playerScore.frames().size()==10);
         assert(playerScore.frames().stream().mapToInt(PlayerScore.Frame::score).sum()==300);
     }
}