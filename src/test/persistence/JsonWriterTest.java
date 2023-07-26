package persistence;

import model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Manager manager = new Manager();
            Team team = new Team(manager);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterFUllGame() {
        try {
            Manager manager = new Manager();
            manager.setName("nima");
            Team team = new Team(manager);
            Player player = new Player(manager, 2);
            player.setName("amin");
            team.addPlayer(player);
            League league = new League();
            league.addTeam(team);
            Fixture fixture = new Fixture(team);
            JsonWriter writer = new JsonWriter("./data/testWriterFullGame.json");
            writer.open();
            JSONObject json = writer.write();
            writer.writeTeam(team, json);
            writer.writeLeague(league, json);
            writer.writeFixture(fixture, json);
            writer.writeManager(manager, json);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFullGame.json");
            Manager manager1 = reader.readManager();
            checkManager(manager1, "nima", 0);
            Team team1 = reader.readTeam(manager1);
            checkTeam(team1, "Dream Team", 0, 1);
            checkPlayer(team1.getPlayers().get(0), "amin", 2, 0);
            Fixture fixture1 = reader.readFixture(team1);
            checkFixture(fixture1, 0, 0);
            League league1 = reader.readLeague(team1);
            checkLeague(league1, "Dream Team", 1);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyGame() {
        try {
            Manager manager = new Manager();
            manager.setName("nima");
            Team team = new Team(manager);
            League league = new League();
            Fixture fixture = new Fixture(team);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGame.json");
            writer.open();
            JSONObject json = writer.write();
            writer.writeTeam(team, json);
            writer.writeLeague(league, json);
            writer.writeFixture(fixture, json);
            writer.writeManager(manager, json);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGame.json");
            Manager manager1 = reader.readManager();
            checkManager(manager1, "nima", 0);
            Team team1 = reader.readTeam(manager1);
            checkTeam(team1, "Dream Team", 0, 0);
            Fixture fixture1 = reader.readFixture(team1);
            checkFixture(fixture1, 0, 0);
            League league1 = reader.readLeague(team1);
            checkLeague(league1, "Dream Team", 1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}