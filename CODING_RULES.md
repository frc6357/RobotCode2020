# Coding Rules
------------

We'll flesh this out into a real coding standard soon but, for now, here are a few rules that programmers should adopt when working in the robot_code repository.

## VSCode IDE Setup
1. Do not commit any changes until VSCode shows no errors or warnings when building the main robot project in master or develop branches. This rule excludes "TODO" comments which report as problems in VSCode but are acceptable during development. Errors are pretty obvious to avoid but warnings are there for a reason and, if you leave any visible, people tend to start ignoring them and, hence, miss the ones that are really important.

2. Indent by 4 spaces.

3. Please don't use tabs in your code since this messes up indentation on other people's editors if they don't have the same settings as you.

4. Fully brace all code. Don't, for example, think you can get away without the braces if you only have a single line following an "if" statement. Although this is syntactically legal, it's a great way to inject unintentional errors.

5. Use Linux-style (LF) line delimiters.

6. Use UTF-8 text file encoding.

## Code Style
7. Use the proper, God's-intended coding style of putting brackets in vertical alignment when writing ifs, elses, methods, classes, etc.

8. Class Names always start with an upper case character (eg. ClassName), method and variable names start with a lower case letter (eg. classMember). Each additional word starts with an upper class letter (eg. LongClassName, longClassMember).

9. In method names, the verb goes first (eg. setSpeed() not speedSet()).

10. All class members should be PRIVATE.

11. All Enums should be named with all capital letters (eg. TEST). All multiword Enums members should be seperated using underscores (eg. PRESSURE_IS_HIGH).

12. No magic numbers!

## JavaDoc Instructions
13. JavaDoc documentation must be created for all classes. Each class will be preceded with a javadoc comment and be followed by a short description of the function of the class. For those interested, html commands like \<p>text\</p> is available. Further information on JavaDoc markup can be found at http://www.write-technical.com/126581/javadoc/javadoc-info.htm.

14. JavaDoc documentation must be created for all class methods. Every method will have documentation according to the following format:

<code>
/**<br/>
 \* The first part of the comment will be a short description of the method's function<br/>
 \* After you've typed your description, it will be followed by the parameters<br/>
 \* @param paramName paramDescription (description must include potential<br/>
 \* values that the parameter can encompass)<br/>
 \* Note: if there are no parameters, type @param None<br/>
 \* @return returnType returnDescription<br/>
 \* Note: if void, type @return Nothing.<br/>
 \* If any exception is thrown, finish with <br/>
 \* @exception exceptionType exceptionDescription (i.e. when it is thrown)<br/>
 */
</code>

EXAMPLE:<br/>
<code>
/**<br/>
 \* This class is an example of how to write javadocs<br/>
 \*/<br/>
public class Test<br/>
{<br/>
<blockquote>
	/**<br/>
	 * This method adds two integers and returns the sum of those integers.<br/>
	 *<br/>
	 * @param num1 The first number to be added.<br/>
	 * @param num2 The second number to be added.<br/>
	 * @return int The sum of the num1 and num2 arguments.<br/>
	 */<br/>
	public static int addNum(int num1, int num2)<br/>
	{<br/>
		<blockquote>
		return num1 + num2;<br/>
		</blockquote>
	}<br/>
	</blockquote>
}
</code>

## Season-specific Notes:
1. Add SK20 to the start of all main subsystem class names.