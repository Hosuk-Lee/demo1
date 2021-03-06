package com.app.base.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@Configuration
public class TransactionConfig {


	private static final int TX_METHOD_TIMEOUT = 10;

	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.*..impl.*Impl.*(..))";

	@Autowired
	private PlatformTransactionManager transactionManager;
	
//	@Autowired
//	private DataSourceTransactionManager transactionManager;

	// 
	// Transaction 설정
	@Bean
	public TransactionInterceptor txAdvice() {

		TransactionInterceptor txAdvice = new TransactionInterceptor();

		Properties txAttributes = new Properties();

		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();

		rollbackRules.add(new RollbackRuleAttribute(Exception.class));

		/** If need to add additional exception, add here **/

		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);

		readOnlyAttribute.setReadOnly(true);

		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);

		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
		
		RuleBasedTransactionAttribute newtranAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW, rollbackRules);

		newtranAttribute.setTimeout(TX_METHOD_TIMEOUT);
		
		/*
		<tx:attributes>
        <tx:method name="*_Tx_Requires_New" propagation="REQUIRES_NEW" rollback-for="Throwable"/>
        <tx:method name="*" propagation="REQUIRED" rollback-for="Throwable"/>
        </tx:attributes>
        */

		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();

		String writeTransactionAttributesDefinition = writeAttribute.toString();
		
		String newtranTransactionAttributesDefinition = newtranAttribute.toString();

		// read-only
		txAttributes.setProperty("retrieve*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("select*"  , readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*"     , readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("list*"    , readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*"  , readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*"    , readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*"   , readOnlyTransactionAttributesDefinition);

		// write rollback-rule
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);

		// new transaction
		txAttributes.setProperty("*_Tx_Requires_New", newtranTransactionAttributesDefinition);

		txAdvice.setTransactionAttributes(txAttributes);
		
		txAdvice.setTransactionManager(transactionManager);

		return txAdvice;

	}

	@Bean
	public Advisor txAdviceAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

		pointcut.setExpression("(execution(* *..*.service..*.*(..)) || execution(* *..*.services..*.*(..)))");

		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, txAdvice());

	}
}
