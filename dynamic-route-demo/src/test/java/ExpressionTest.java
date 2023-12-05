import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.ast.OpEQ;
import org.springframework.expression.spel.ast.OpGT;
import org.springframework.expression.spel.ast.OpNE;
import org.springframework.expression.spel.standard.SpelCompiler;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTest {

    public static void main(String[] args) {
        // 创建一个SpelExpressionParser
        SpelExpressionParser parser = new SpelExpressionParser();

        // 定义一个逻辑表达式
        String logicalExpression = "(systemname == 1 || systemname == 2) && (status == 'active' || status != 'inactive') && (count > 5)";

        // 解析逻辑表达式
        SpelExpression expression = (SpelExpression) parser.parseExpression(logicalExpression);

        final SpelNode ast = expression.getAST();
        final Object rightHandValue = getRightHandValue(ast);
        // 输出结果
        System.out.println(rightHandValue);
    }

    private static List<Object> getRightHandValue(SpelNode node) {
        List<Object> values = new ArrayList<>();
        if (node instanceof OpEQ || node instanceof OpNE || node instanceof OpGT) {
            // 如果是等号节点，返回右侧节点的值
            values.add(node.getChild(1));
        } else {
            // 递归处理子节点
            for (int i = 0; i < node.getChildCount(); i++) {
                values.addAll(getRightHandValue(node.getChild(i)));
            }
        }
        return values;
    }

}
