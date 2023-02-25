import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QuizProgram {
    public static void main(String[] args) throws IOException, ParseException {
         char ch = 'y';
         String fileName = "./src/main/resources/QuestionList.json";
         Scanner sc = new Scanner(System.in);
         System.out.println("1. Add Quiz");
         System.out.println("2. Start Quiz");
         int choose = sc.nextInt();
         sc.nextLine();
             if(choose == 1){
                     do{
                         JSONParser jsonParser = new JSONParser();
                         JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(fileName));
//                         LinkedHashMap<String, Object> quesObj = new LinkedHashMap<String, Object>();
                         JSONObject quesObj = new JSONObject();

                         System.out.println("Please add a question here: ");
                         quesObj.put("Question", sc.nextLine());

                         System.out.println("Input options: ");

                         System.out.println("Option a: ");
                         quesObj.put("a:", sc.nextLine());

                         System.out.println("Option b: ");
                         quesObj.put("b:", sc.nextLine());

                         System.out.println("Option c: ");
                         quesObj.put("c:", sc.nextLine());

                         System.out.println("Option d: ");
                         quesObj.put("d:", sc.nextLine());

                         System.out.println("Please input the correct ans: ");
                         quesObj.put("Correct:", sc.nextLine());

                         jsonArray.add(quesObj);


                         FileWriter fileWriter = new FileWriter(fileName);
                         fileWriter.write(jsonArray.toJSONString());
                         fileWriter.flush();
                         fileWriter.close();

                         System.out.println("Quiz saved at the database. Do you want to add more? (y/n)");
                         ch = sc.next().charAt(0);
                         sc.nextLine();
                     }
                    while(ch != 'n');
             }

             else if(choose == 2){
                 JSONParser jsonParser = new JSONParser();
                 JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(fileName));

                 List<JSONObject> questions = new ArrayList<JSONObject>();
                 for (Object obj : jsonArray) {
                     JSONObject question = (JSONObject) obj;
                     questions.add(question);

                 }
                 System.out.println("You will be asked 5 questions, each questions has 1 marks: ");
                 Collections.shuffle(questions);
                 int correctAnswers = 0;
                 for (int i = 0; i < 5; i++) {
                     JSONObject question = questions.get(i);
                     String questionText = (String) question.get("Question");
                     System.out.println("\n" + (i + 1) + ": " + questionText);
                     String[] options = { "a", "b", "c", "d" };
                     Collections.shuffle(Arrays.asList(options));
                     for (String option : options) {
                         String optionText = (String) question.get(option + ":");
                         System.out.println(option.toUpperCase() + ") " + optionText);
                     }

                     String answer = sc.nextLine();
                     String correctAnswer = (String) question.get("Correct:");
                     if (answer.equals(correctAnswer)) {
                         System.out.println("Correct!");
                         correctAnswers++;
                     } else {
                         System.out.println("Not correct ");
                     }
                 }
                 System.out.println("\nYou got: " + correctAnswers + " out of 5");

             }
    }
}