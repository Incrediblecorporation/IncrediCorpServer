package incredicorpserver.webservice;

import incredicorpserver.beans.BTD_Score;
import incredicorpserver.repository.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/btd_score")
public class BTD_ScoreWebservice {

    @Autowired
    UserRepository userRepo;
    @Autowired
    BTD_ScoreRepository btd_scoreRepository;
    @Autowired
    BTD_LevelRepository btd_levelRepository;
    @Autowired
    BTD_ModeRepository btd_modeRepository;
    @Autowired
    BTD_DifficultyRepository btd_difficultyRepository;


    //--POST NEW SCORE-- returns W_Score -
    @RequestMapping(value="/new", method= RequestMethod.POST)
    public ResponseEntity<List> createScore(@RequestBody BTD_Score score) {
        score = btd_scoreRepository.save(score);
        return new ResponseEntity<>(cleanListOfScore(score), HttpStatus.OK);
    }

    //--GET SPECIFIC SCORES-- returns JSONObject{List<BTD_Score>} -
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/getSpecific",method= RequestMethod.GET)
    public ResponseEntity<JSONObject> getSpecificScores(@RequestParam(required=true) int mode, int difficulty, int level, String name) {
        JSONObject toSend = new JSONObject();
        JSONArray entries = new JSONArray();

        List<BTD_Score> listOfScores = btd_scoreRepository.findByLevelAndModeAndDifficulty(btd_levelRepository.findByLevelId(level), btd_modeRepository.findByModeId(mode), btd_difficultyRepository.findByDifficultyId(difficulty));
        ScoreComparator2 comp = new ScoreComparator2();
        listOfScores.sort(comp);
/*
        System.out.println("Requested MODE = "+ mode);
        System.out.println("Requested LEVEL = "+ level);
        System.out.println("Requested NAME = "+ name);
*/
        int ind = 0;

        for (int i=0;i<listOfScores.size();i++) {
            if (name.equals("noone")) {
                //System.out.println("Found = "+ listOfScores.get(i).getScoreRank()+" "+listOfScores.get(i).getScoreValue()+" "+listOfScores.get(i).getWave()+" "+listOfScores.get(i).getUser().getUserName());

                JSONObject score = new JSONObject();
                score.put("score_rank",listOfScores.get(i).getScoreRank());
                score.put("score_value",listOfScores.get(i).getScoreValue());
                score.put("score_wave",listOfScores.get(i).getWave());
                score.put("user_name",listOfScores.get(i).getUser().getUserName());
                entries.add(ind,score);
                ind++;
            } else {
                if (name.equals(listOfScores.get(i).getUser().getUserName())) {
                    //System.out.println("Found = "+ listOfScores.get(i).getScoreRank()+" "+listOfScores.get(i).getScoreValue()+" "+listOfScores.get(i).getWave()+" "+listOfScores.get(i).getUser().getUserName());

                    JSONObject score = new JSONObject();
                    score.put("score_rank", listOfScores.get(i).getScoreRank());
                    score.put("score_value", listOfScores.get(i).getScoreValue());
                    score.put("score_wave",listOfScores.get(i).getWave());
                    score.put("user_name",listOfScores.get(i).getUser().getUserName());
                    entries.add(ind, score);
                    ind++;
                }
            }
        }
        toSend.put("entries",entries);

        return new ResponseEntity<>(toSend, HttpStatus.OK);
    }

    //--GET ALL SCORES-- returns JSONObject{List<W_Score>} -
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/getAll",method= RequestMethod.GET)
    public ResponseEntity<JSONObject> getAllScores() {

        List<BTD_Score> listOfScores = btd_scoreRepository.findAll();
        ScoreComparator2 comp = new ScoreComparator2();
        listOfScores.sort(comp);

        JSONObject toSend = new JSONObject();
        JSONArray entries = new JSONArray();

        for (int i=0;i<listOfScores.size();i++) {
            JSONObject score = new JSONObject();
            score.put("score_rank",listOfScores.get(i).getScoreRank());
            score.put("score_value",listOfScores.get(i).getScoreValue());
            score.put("score_wave",listOfScores.get(i).getWave());
            score.put("user_name",listOfScores.get(i).getUser().getUserName());
            entries.add(i,score);
        }
        toSend.put("entries",entries);

        return new ResponseEntity<>(toSend, HttpStatus.OK);
    }

    //COMPARE BTD_Scores by VALUE
    public class ScoreComparator implements Comparator<BTD_Score> {
        @Override
        public int compare(BTD_Score o1,BTD_Score o2) {
            return o1.getScoreValue().compareTo(o2.getScoreValue());
        }
    }

    //COMPARE BTD_Scores by RANK
    public class ScoreComparator2 implements Comparator<BTD_Score> {
        @Override
        public int compare(BTD_Score o1,BTD_Score o2) {
            return o1.getScoreRank().compareTo(o2.getScoreRank());
        }
    }

    //REFRESH SCORES and GET WANTED SCORES
    //Sets new ranking for involved scores
    //Returns a shorten list of scores:
    //  the highest ranked Score
    //  the X Scores ranked above the new Score
    //  the X Scores ranked below the new Score
    public List<BTD_Score> cleanListOfScore(BTD_Score score){
        List<BTD_Score> listToSend = new ArrayList<>();
        List<BTD_Score> listOfScores = btd_scoreRepository.findByLevelAndModeAndDifficulty(score.getLevel(),score.getMode(),score.getDifficulty());
        ScoreComparator comp = new ScoreComparator();
        listOfScores.sort(comp);
        int pos = listOfScores.indexOf(score);
        int delta = 2;
        for (int i=0;i<listOfScores.size();i++) {
            listOfScores.get(i).setScoreRank(listOfScores.size()-i);
            btd_scoreRepository.save(listOfScores.get(i));
            if (i != pos)
                listOfScores.get(i).setScoreNew(false);
            if (pos-delta <= i && i <= pos+delta)
                listToSend.add(listOfScores.get(i));
        }
        listToSend.add(listOfScores.get(listOfScores.size()-1));
        ScoreComparator2 comp2 = new ScoreComparator2();
        listToSend.sort(comp2);
        return listToSend;
    }
}
