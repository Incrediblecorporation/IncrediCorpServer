package incredicorpserver;

import incredicorpserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UserRepository repoUser;

	@Autowired
	W_LevelRepository repoLevel;

	@Autowired
	W_ModeRepository repoMode;

	@Autowired
	W_ScoreRepository repoScore;

	@Autowired
	BTD_LevelRepository btd_repoLevel;

	@Autowired
	BTD_ModeRepository btd_repoMode;

	@Autowired
	BTD_ScoreRepository btd_repoScore;

	@Autowired
	BTD_DifficultyRepository btd_repoDifficulty;

	/*
	//Compare WORDAZING scores in the list in order to sort it by SCORE_VALUE
	public class ScoreComparator implements Comparator<W_Score> {
		@Override
		public int compare(W_Score o1,W_Score o2) {
			return o1.getScoreValue().compareTo(o2.getScoreValue());
		}
	}
	//Compare BADASS TOWER DEFENSE scores in the list in order to sort it by SCORE_VALUE
	public class BTD_ScoreComparator implements Comparator<BTD_Score> {
		@Override
		public int compare(BTD_Score o1,BTD_Score o2) {
			return o1.getScoreValue().compareTo(o2.getScoreValue());
		}
	}
	*/
	/*
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/w_score/get_all").allowedOrigins("*");
                }
            };
        }
    */
	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
/*
			//--POPULATE--
			//Users
			User olivio = repoUser.save(new User("0livio", "oneumayer@hotmail.fr", "bigobigo"));
			User tom = repoUser.save(new User("Tom", "tom@hotmail.fr", "tomtomtom"));
			User Olivio = repoUser.save(new User("Olivio", "oneumayer@hotmail.com", "bigobigo"));
			User one = repoUser.save(new User("1111", "1111@1111.1111", "11111111"));
			User two = repoUser.save(new User("2222", "2222@2222.2222", "22222222"));
			User three = repoUser.save(new User("3333", "3333@3333.3333", "33333333"));

			//WORADZING Levels
			W_Level level_1 = repoLevel.save(new W_Level("1"));
			W_Level level_2 = repoLevel.save(new W_Level("2"));
			W_Level level_3 = repoLevel.save(new W_Level("3"));
			W_Level level_4 = repoLevel.save(new W_Level("4"));
			W_Level level_5 = repoLevel.save(new W_Level("5"));
			W_Level level_6 = repoLevel.save(new W_Level("6"));
			W_Level level_7 = repoLevel.save(new W_Level("7"));
			W_Level level_8 = repoLevel.save(new W_Level("8"));
			W_Level level_9 = repoLevel.save(new W_Level("9"));
			W_Level level_10 = repoLevel.save(new W_Level("10"));

			//WORDAZING Modes
			W_Mode mode_1 = repoMode.save(new W_Mode("1"));
			W_Mode mode_2 = repoMode.save(new W_Mode("2"));
			W_Mode mode_3 = repoMode.save(new W_Mode("3"));
			W_Mode mode_4 = repoMode.save(new W_Mode("4"));
			W_Mode mode_5 = repoMode.save(new W_Mode("5"));
			W_Mode mode_6 = repoMode.save(new W_Mode("6"));
			W_Mode mode_7 = repoMode.save(new W_Mode("7"));
			W_Mode mode_8 = repoMode.save(new W_Mode("8"));
			W_Mode mode_9 = repoMode.save(new W_Mode("9"));
			W_Mode mode_10 = repoMode.save(new W_Mode("10"));
			W_Mode mode_11 = repoMode.save(new W_Mode("11"));//Mode de base
			W_Mode mode_12 = repoMode.save(new W_Mode("12"));
			W_Mode mode_13 = repoMode.save(new W_Mode("13"));
			W_Mode mode_14 = repoMode.save(new W_Mode("14"));
			W_Mode mode_15 = repoMode.save(new W_Mode("15"));
			W_Mode mode_16 = repoMode.save(new W_Mode("16"));
*//*
			//WORDAZING Scores
			int random_user;
			int random_score;
			String language = "fr";
*//*
			for (int i=1;i<=16;i++){
				for (int j=1;j<=10;j++){
					for (int k=0;k<4;k++) {
						switch (k) {
							case 0: language = "fr"; break;
							case 1: language = "en"; break;
							case 2: language = "sp"; break;
							case 3: language = "de"; break;
						}
						for (int l=0;l<=10;l++) {
							random_user = (int) (Math.random() * 5+1);
							random_score = (int) (Math.random() * 700)+300;
							repoScore.save(new W_Score(0,random_score,language,false,repoUser.findByUserId(random_user),repoMode.findByModeId(i),repoLevel.findByLevelId(j)));
						}
					}
				}
			}*/
/*
			//WORDAZING SORT SCORES & setSCORE RANK
			ScoreComparator comp = new ScoreComparator();
			for (int i=1;i<=16;i++){ //Modes
				for (int j=1;j<=10;j++){ //Levels
					for (int k=0;k<4;k++) { //languages
						switch (k) {
							case 0: language = "fr"; break;
							case 1: language = "en"; break;
							case 2: language = "sp"; break;
							case 3: language = "de"; break;
						}
						List<W_Score> listToSort = new ArrayList<>();
						listToSort = repoScore.findByLevelAndModeAndScoreL(repoLevel.findByLevelId(j),repoMode.findByModeId(i),language);
						listToSort.sort(comp);

						System.out.println("MODE = "+ i);
						System.out.println("LEVEL = "+ j);
						System.out.println("LANGUAGE = "+ language);
						System.out.println("Size = "+ listToSort.size());
						for (int m=0;m<listToSort.size();m++){
							listToSort.get(m).setScoreRank(listToSort.size()-m);
							repoScore.save(listToSort.get(m));
						}
					}
				}
			}
*/
			/*
			//BADASS TD
			int random_wave;
			int random_user;
			int random_score;



			for (int i=0;i<=1;i++){ //Modes
				String mode_name = "arcade";
				if (i == 0) {
					mode_name = "campain";
				}
				btd_repoMode.save(new BTD_Mode(mode_name));
			}
			for (int j=1;j<=3;j++) { //Difficulties
				btd_repoDifficulty.save(new BTD_Difficulty(Integer.toString(j)));
			}
			for (int k=1;k<=60;k++) { //Levels
				btd_repoLevel.save(new BTD_Level(Integer.toString(k)));
			}
			for (int i=0;i<=1;i++){ //Modes
				BTD_Mode btd_mode = btd_repoMode.findByModeId(i+1);
				for (int j=1;j<=3;j++){ //Difficulties
					BTD_Difficulty btd_difficulty = btd_repoDifficulty.findByDifficultyId(j);
					for (int k=1;k<=60;k++) { //Levels
						BTD_Level btd_level = btd_repoLevel.findByLevelId(k);
						for (int l=1;l<=5;l++) {
							random_wave = (int) (Math.random() * 10+10);
							random_user = (int) (Math.random() * 5+1);
							random_score = (int) (Math.random() * 4000)+3000;
							btd_repoScore.save(new BTD_Score(0,random_score,false,repoUser.findByUserId(random_user),btd_mode,btd_difficulty,btd_level,random_wave));
						}
					}
				}
			}*/
/*
			//BADASS TD SORT SCORES & setSCORE RANK
			BTD_ScoreComparator comp = new BTD_ScoreComparator();
			for (int i=1;i<=2;i++){ //Modes
				for (int j=1;j<=30;j++){ //Levels
					for (int k=1;k<3;k++) { //Difficulties
						List<BTD_Score> listToSort = new ArrayList<>();
						listToSort = btd_repoScore.findByLevelAndModeAndDifficulty(btd_repoLevel.findByLevelId(j),btd_repoMode.findByModeId(i),btd_repoDifficulty.findByDifficultyId(k));
						listToSort.sort(comp);

						System.out.println("MODE = "+ i);
						System.out.println("LEVEL = "+ j);
						System.out.println("DIFFICULTY = "+ k);
						System.out.println("Size = "+ listToSort.size());
						for (int m=0;m<listToSort.size();m++){
							listToSort.get(m).setScoreRank(listToSort.size()-m);
							btd_repoScore.save(listToSort.get(m));
						}
					}
				}
			}
			*/
		};
	}
}