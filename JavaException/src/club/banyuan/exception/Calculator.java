package club.banyuan.exception;

import java.util.Scanner;

/**
 * Calculator类提供了用于解析包含简单表达式的输入字符串以及计算表达式结果的功能。
 */

public class Calculator {

  public Calculator() {
  }

  /**
   * 0 个符号: IllegalInputException: "Illegal Token Length"
   * 1 个符号:
   * "quit" (不区分大小写): QuitException
   * 所有其他情况: IllegalInputException: "Illegal Argument"
   * 2 个符号:
   * "-" 和数字: 返回负数
   * "-" 和非数字: IllegalInputException: "Illegal Argument"
   * 其他字符串: IllegalInputException: "Illegal Operator"
   * 3 个符号:
   * 数字1, "+", 数字2: 返回两个数字的和
   * 数字1, "/", 0: DivideByZeroException
   * 数字1, "/", 非零数字:  返回两个数字的商
   * 非数字，其他输入，其他输入: IllegalInputException: "Illegal Argument"
   * 数字1, 其他输入, 非数字: IllegalInputException: "Illegal Argument"
   * 数字1, 非运算符, 数字2: IllegalInputException: "Illegal Operator"
   * 4 更多的运算符: IllegalInputException: "Illegal Token Length"
   * <p>
   * Note: 所有数字都是整数
   *
   * @param tokens 符号数组，包含用户输入的操作数或操作符
   * @return 计算表达式的结果
   * @throws CalculatorException 如果用户输入不合法，或者已键入“ quit”。抛出CalculatorException 的几个子类之一，以便提供有关错误原因的更多信息.
   */
  public static int compute(String[] tokens) throws CalculatorException {
    // 不同符号数量的各种情况
    switch (tokens.length) {
      case 0:
        // TODO: complete the cases
        throw new IllegalInputException("Illegal input : Illegal Token Length");
      case 1:
        // 只有一种情况，用户输入 quit
        // TODO: complete the cases
        if (tokens[0].equalsIgnoreCase("quit")){
          throw new QuitException("Quitting\nThanks");
        }else{
          throw new IllegalInputException("Illegal input : Illegal Argument");
        }

      case 2:
        // 只有一种情况，用户输入 负数
        // TODO: complete the cases
        Integer res = Check(tokens[1]);
        if (tokens[0].equals("-")&& res!=null){
          return -res;
        }else {
          throw new IllegalInputException("Illegal input : Illegal Argument");
        }
      case 3:
        // 计算表达式
        // TODO: complete the cases
        Integer a =Check(tokens[0]);
        Integer b =Check(tokens[2]);
        if (a==null || b == null){
          throw new IllegalInputException("Illegal input : Illegal Argument");
        } else if (tokens[1].equals("+") || tokens[1].equals("-") || tokens[1].equals("/") || tokens[1].equals("*")){
          if (tokens[1].equals("/") && b.equals(0)){
            throw new DivideByZeroException("Tried to divide by zero");
          }else{
            switch (tokens[1]){
              case "+":
                return a + b;
              case "-":
                return a - b;
              case "/":
                return a / b;
              case "*":
                return a * b;
            }
          }
        }else {
          throw new IllegalInputException("Illegal input : Illegal Operator");
        }

      default:
        // 4个或等多操作符号抛出异常
        // TODO: complete the cases
        throw new IllegalInputException("Illegal input : Illegal Token Length");
    }
  }
  public static Integer Check(String str){
    char[] chars = str.toCharArray();
    Integer integer = 0;
    for (char c : chars) {
      if (Character.isDigit(c)){
        integer = integer*10+((int)c-48);
      }else {
        return null;
      }
    }
    return integer;
  }

  /**
   * 将输入字符串解析为表达式并对其求值。
   * <p>
   * 如果输入是合法表达式，则打印结果
   * <p>
   * 否则抛出CalculatorException。根据异常类型打印消息。
   * <p>
   * 始终打印出输入内容。为此使用finally块。
   * <p>
   * 始终遵循以下规则打印两行：
   * 第一行:
   * -No Exception: "The result is: " + result
   * -QuitException: "Quitting"
   * -DivideByZeroException: "Tried to divide by zero"
   * -IllegalInputException: "Illegal input: " + illegalinputtype(given to constructor)
   * -CalculatorException: <should never happen> e.getMessage()
   * 第二行:
   * "Input was: " + input
   *
   * @param input 可能包含数学表达式的字符串
   * @return true 如果String等于"quit"; false 其他情况
   */
  public static boolean parseAndCompute(String input) {
    // 提取所有运算符号

    String[] tokens = input.split(" ");
    try {
      // TODO: complete implementation.
      System.out.println("The result is :"+compute(tokens));
    } catch (QuitException e) {
      System.out.println(e.getMessage());
      return true;
      // TODO: complete implementation.
    } catch (IllegalInputException e) {
      // TODO: complete implementation.
      System.out.println(e.getMessage());
    } catch (CalculatorException e) {
      // 这捕获了剩下的CalculatorException情况：DivideByZeroException
      // TODO: complete implementation.
      System.out.println(e.getMessage());
    }finally {
      System.out.println("Input was:" + input);
    }

    // TODO: complete implementation.
    // 未指定退出
    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str;
    do{
      str = scanner.nextLine();
    } while (!parseAndCompute(str));
  }
}
