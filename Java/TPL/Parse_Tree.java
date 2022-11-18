import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parse_Tree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Expression: ");
        String input = in.nextLine();
        in.close();
        System.out.println(result(input));

    }

    public static String result(String input) {
        String expression = "+-%/*";
        String[] specific = input.split(" ");
        String result = "";
        for (int i = 0;;) {
            if (specific[i].equals("int")) {
                if (specific[2].equals("=") && specific[specific.length - 1].equals(";")
                        && !expression.contains(specific[specific.length - 2])
                        && !specific[specific.length - 2].equals("=")) {
                    System.out.println("         <assign>         ");
                    System.out.println("         /      \\          ");
                    System.out.println("<data_type>      <identifier> =   <value>");
                    System.out.println("     |\t\t\t|\t /      \\");
                    System.out.print("    " + specific[0] + "\t\t\t" + specific[1]);
                    String[] element = new String[specific.length];
                    for (int j = 3; j < element.length; j++) {
                        element[j] = specific[j];
                    }

                    boolean validNum = false;
                    boolean validExpr = false;
                    for (int j = 3; j < element.length; j++) {
                        char[] elemChar = element[j].toCharArray();
                        for (char c : elemChar) {
                            if (Character.isDigit(c)) {
                                validNum = true;
                            } else {
                                validNum = false;
                            }
                        }
                        try {
                            while (element[j + 1] != null) {
                                if (expression.contains(element[j + 1])) {
                                    validExpr = true;
                                    break;
                                } else {
                                    validExpr = false;
                                    break;
                                }
                            }
                            List<String> temp = new ArrayList<>();
                            String output = "";
                            if (validNum == true && validExpr == true) {
                                if (j == 3) {
                                    output += ("\t" + element[j] + "\t<expression>");
                                    output += ("\n" + addSpaces() + "/     \\");
                                    temp.add(output);
                                    output = "";
                                } else {
                                    output += (addSpaces() + element[j] + "\t<expression>\n");
                                    output += ("\n" + addSpaces() + "/     \\");
                                    temp.add(output);
                                    output = "";
                                }
                            } else if (validNum = true && validExpr == false && element[j + 1].equals(";")) {
                                output += (addSpaces() + element[j] + "\t<delimiter>");
                                output += ("\n" + addSpaces() + ";");
                                temp.add(output);
                                output = "";
                            } else if (validNum == false && validExpr == false) {
                                output += (addSpaces() + element[j] + "\t<value>");
                                output += ("\n" + addSpaces() + "/     \\");
                                temp.add(output);
                                output = "";
                            }
                            for (String string : temp) {
                                System.out.println(string);
                            }
                        } catch (Exception e) {
                        }
                    }
                    System.out.println();
                    result = "Semantically Correct!";
                    break;
                } else {
                    result = "Semantically Incorrect!";
                    break;
                }
            } else if (specific[i].equals("double")) {
                if (specific[2].equals("=") && specific[specific.length - 1].equals(";")) {
                    char[] chars = specific[3].toCharArray();
                    boolean num = false;
                    for (char c : chars) {
                        if (Character.isDigit(c)) {
                            num = true;
                        }
                    }
                    if (num == true && specific[3].contains(".")) {
                        result = "Semantically correct!";
                    }
                    break;
                } else {
                    result = "Semantically Incorrect!";
                    break;
                }
            } else if (specific[i].equals("String")) {
                if (specific[2].equals("=")
                        && Character.toString(specific[3].charAt(0)).equals("\"")
                        && Character.toString(
                                specific[specific.length - 2].charAt(specific[specific.length - 2].length() - 1))
                                .equals("\"")
                        && specific[specific.length - 1].equals(";")) {
                    result = "Semantically Correct!";
                    break;
                } else {
                    result = "Semantically Incorrect!";
                    break;
                }
            } else if (specific[i].equals("char")) {
                if (specific[2].equals("=")
                        && (Character.toString(specific[3].charAt(0)).equals("\'")
                                && Character.toString(specific[3].charAt(2)).equals("\'")
                                || specific[3].length() == 1)
                        && specific[specific.length - 1].equals(";")) {

                    result = "Semantically Correct!";
                    break;
                } else {
                    result = "Semantically Incorrect!";
                    break;
                }
            } else if (specific[i].equals("boolean")) {
                String bol = "true True TRUE false False FALSE";
                if (specific[2].equals("=") && bol.contains(specific[3])
                        && specific[specific.length - 1].equals(";")) {
                    result = "Semantically Correct!";
                    break;
                } else {
                    result = "Semantically Incorrect!";
                    break;
                }
            } else {
                result = "Semantically Incorrect!";
                break;
            }
        }
        return result;
    }

    public static String addSpaces() {
        String addedTab = "                            ";
        return addedTab;
    }
}