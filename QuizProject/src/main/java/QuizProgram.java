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
                 Object obj = jsonParser.parse(new FileReader(fileName));
                 JSONArray jsonArray = (JSONArray) obj;

                 System.out.println("You will be asked 5 questions, each question has 1 mark: ");
//                 String correct = (String) quesObj.get("Correct");

                 int correctAnswers = 0;
                 for (int i = 0; i < 5 && jsonArray.size() > 0; i++) {
//                     int pos;
//                     do {
//                         pos = new Random().nextInt(jsonArray.size());
//                     } while (usedPositions.contains(pos));
//                     usedPositions.add(pos);

                     int pos = new Random().nextInt(jsonArray.size());
                     JSONObject quesObj = (JSONObject) jsonArray.get(pos);

                     String question = (String) quesObj.get("Question");
                     String optionA = (String) quesObj.get("a:");
                     String optionB = (String) quesObj.get("b:");
                     String optionC = (String) quesObj.get("c:");
                     String optionD = (String) quesObj.get("d:");
                     String correct = (String) quesObj.get("Correct:");

                     System.out.println("\n" + (i + 1) + ": " + question);
                     System.out.println("a. " + optionA);
                     System.out.println("b. " + optionB);
                     System.out.println("c. " + optionC);
                     System.out.println("d. " + optionD);


                     String answer = sc.nextLine();
//                     String correctAnswer = (String) quesObj.get("Correct:");
                     if (answer.contains(correct)) {
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