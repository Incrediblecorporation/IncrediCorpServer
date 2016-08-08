package incredicorpserver.webservice;

import incredicorpserver.beans.W_Score;
import incredicorpserver.repository.UserRepository;
import incredicorpserver.repository.W_LevelRepository;
import incredicorpserver.repository.W_ModeRepository;
import incredicorpserver.repository.W_ScoreRepository;
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
@RequestMapping("/w_score")
public class W_Score_Webservice {

    @Autowired
    UserRepository userRepo;
    @Autowired
    W_ScoreRepository w_scoreRepo;
    @Autowired
    W_LevelRepository w_levelRepo;
    @Autowired
    W_ModeRepository w_modeRepo;


    //--POST NEW SCORE-- returns W_Score -
    @RequestMapping(value="/new", method= RequestMethod.POST)
    public ResponseEntity<List> createScore(@RequestBody W_Score score) {
        score = w_scoreRepo.save(score);
        return new ResponseEntity<>(cleanListOfScore(score), HttpStatus.OK);
    }

    //--GET SPECIFIC SCORES-- returns JSONObject{List<W_Score>} -
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/getSpecific",method= RequestMethod.GET)
    public ResponseEntity<JSONObject> getSpecificScores(@RequestParam(required=true) Boolean mode_b, Boolean mode_r, Boolean mode_s, Boolean mode_t, int level, String name, String language) {
        JSONObject toSend = new JSONObject();
        JSONArray entries = new JSONArray();
        int mode = 0;

        if ((!mode_s) && (!mode_t) && (!mode_b) && (!mode_r)){
            mode = 1;
        } else if ((!mode_s) && (!mode_t) && (!mode_b) && (mode_r)){
            mode = 2;
        } else if ((!mode_s) && (!mode_t) && (mode_b) && (!mode_r)){
            mode = 3;
        } else if ((!mode_s) && (!mode_t) && (mode_b) && (mode_r)){
            mode = 4;
        } else if ((!mode_s) && (mode_t) && (!mode_b) && (!mode_r)){
            mode = 5;
        } else if ((!mode_s) && (mode_t) && (!mode_b) && (mode_r)){
            mode = 6;
        } else if ((!mode_s) && (mode_t) && (mode_b) && (!mode_r)){
            mode = 7;
        } else if ((!mode_s) && (mode_t) && (mode_b) && (mode_r)){
            mode = 8;
        } else if ((mode_s) && (!mode_t) && (!mode_b) && (!mode_r)){
            mode = 9;
        } else if ((mode_s) && (!mode_t) && (!mode_b) && (mode_r)){
            mode = 10;
        } else if ((mode_s) && (!mode_t) && (mode_b) && (!mode_r)){
            mode = 11;
        } else if ((mode_s) && (!mode_t) && (mode_b) && (mode_r)){
            mode = 12;
        } else if ((mode_s) && (mode_t) && (!mode_b) && (!mode_r)){
            mode = 13;
        } else if ((mode_s) && (mode_t) && (!mode_b) && (mode_r)){
            mode = 14;
        } else if ((mode_s) && (mode_t) && (mode_b) && (!mode_r)){
            mode = 15;
        } else if (mode_s && mode_t && mode_b && mode_r){
            mode = 16;
        }

        List<W_Score> listOfScores = w_scoreRepo.findByLevelAndModeAndScoreL(w_levelRepo.findByLevelId(level), w_modeRepo.findByModeId(mode),language);
        ScoreComparator2 comp = new ScoreComparator2();
        listOfScores.sort(comp);

        /*System.out.println("Requested MODE = "+ mode);
        System.out.println("Requested LEVEL = "+ level);
        System.out.println("Requested NAME = "+ name);*/

        int ind = 0;

        for (int i=0;i<listOfScores.size();i++) {
            if (name.equals("noone")) {
                JSONObject score = new JSONObject();
                score.put("score_rank",listOfScores.get(i).getScoreRank());
                score.put("score_value",listOfScores.get(i).getScoreValue());
                score.put("user_name",listOfScores.get(i).getUser().getUserName());
                entries.add(ind,score);
                ind++;
            } else {
                if (name.equals(listOfScores.get(i).getUser().getUserName())) {
                    JSONObject score = new JSONObject();
                    score.put("score_rank", listOfScores.get(i).getScoreRank());
                    score.put("score_value", listOfScores.get(i).getScoreValue());
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

        List<W_Score> listOfScores = w_scoreRepo.findAll();
        ScoreComparator2 comp = new ScoreComparator2();
        listOfScores.sort(comp);

        JSONObject toSend = new JSONObject();
        JSONArray entries = new JSONArray();

        for (int i=0;i<listOfScores.size();i++) {
            JSONObject score = new JSONObject();
            score.put("score_rank",listOfScores.get(i).getScoreRank());
            score.put("score_value",listOfScores.get(i).getScoreValue());
            score.put("user_name",listOfScores.get(i).getUser().getUserName());
            entries.add(i,score);
        }
        toSend.put("entries",entries);

        return new ResponseEntity<>(toSend, HttpStatus.OK);
    }

    //COMPARE W_Scores by VALUE
    public class ScoreComparator implements Comparator<W_Score> {
        @Override
        public int compare(W_Score o1,W_Score o2) {
            return o1.getScoreValue().compareTo(o2.getScoreValue());
        }
    }

    //COMPARE W_Scores by RANK
    public class ScoreComparator2 implements Comparator<W_Score> {
        @Override
        public int compare(W_Score o1,W_Score o2) {
            return o1.getScoreRank().compareTo(o2.getScoreRank());
        }
    }

    //REFRESH SCORES and GET WANTED SCORES
    //Sets new ranking for involved scores
    //Returns a shorten list of scores:
    //  the highest ranked Score
    //  the X Scores ranked above the new Score
    //  the X Scores ranked below the new Score
    public List<W_Score> cleanListOfScore(W_Score score){
        List<W_Score> listToSend = new ArrayList<>();
        List<W_Score> listOfScores = w_scoreRepo.findByLevelAndModeAndScoreL(score.getLevel(),score.getMode(),score.getScoreL());
        ScoreComparator comp = new ScoreComparator();
        listOfScores.sort(comp);
        int pos = listOfScores.indexOf(score);
        int delta = 2;
        for (int i=0;i<listOfScores.size();i++) {
            listOfScores.get(i).setScoreRank(listOfScores.size()-i);
            w_scoreRepo.save(listOfScores.get(i));
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
