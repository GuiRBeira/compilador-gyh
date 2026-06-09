// Generated from src/gyh/GYH.g4 by ANTLR 4.13.2

package gyh.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GYHParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PCDec=1, PCProg=2, PCInt=3, PCReal=4, PCLer=5, PCImprimir=6, PCSe=7, PCEntao=8, 
		PCSenao=9, PCEnqto=10, PCIni=11, PCFim=12, OpBoolE=13, OpBoolOu=14, OpAritMult=15, 
		OpAritDiv=16, OpAritSoma=17, OpAritSub=18, OpRelMenorIgual=19, OpRelMenor=20, 
		OpRelMaiorIgual=21, OpRelMaior=22, OpRelIgual=23, OpRelDif=24, Delim=25, 
		Atrib=26, AbrePar=27, FechaPar=28, Var=29, NumReal=30, NumInt=31, Cadeia=32, 
		WS=33, COMMENT=34, INVALID_KEYWORD=35, UNKNOWN=36;
	public static final int
		RULE_programa = 0, RULE_declarations = 1, RULE_variableDeclaration = 2, 
		RULE_type = 3, RULE_statementList = 4, RULE_statement = 5, RULE_assignment = 6, 
		RULE_readCommand = 7, RULE_printCommand = 8, RULE_ifCommand = 9, RULE_whileCommand = 10, 
		RULE_subAlgorithm = 11, RULE_expression = 12, RULE_logicalExpression = 13, 
		RULE_relationalExpression = 14, RULE_arithmeticExpression = 15, RULE_term = 16, 
		RULE_factor = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declarations", "variableDeclaration", "type", "statementList", 
			"statement", "assignment", "readCommand", "printCommand", "ifCommand", 
			"whileCommand", "subAlgorithm", "expression", "logicalExpression", "relationalExpression", 
			"arithmeticExpression", "term", "factor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'DEC'", "'PROG'", "'INT'", "'REAL'", "'LER'", "'IMPRIMIR'", "'SE'", 
			"'ENTAO'", "'SENAO'", "'ENQTO'", "'INI'", "'FIM'", "'E'", "'OU'", "'*'", 
			"'/'", "'+'", "'-'", "'<='", "'<'", "'>='", "'>'", "'=='", "'!='", "':'", 
			"':='", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PCDec", "PCProg", "PCInt", "PCReal", "PCLer", "PCImprimir", "PCSe", 
			"PCEntao", "PCSenao", "PCEnqto", "PCIni", "PCFim", "OpBoolE", "OpBoolOu", 
			"OpAritMult", "OpAritDiv", "OpAritSoma", "OpAritSub", "OpRelMenorIgual", 
			"OpRelMenor", "OpRelMaiorIgual", "OpRelMaior", "OpRelIgual", "OpRelDif", 
			"Delim", "Atrib", "AbrePar", "FechaPar", "Var", "NumReal", "NumInt", 
			"Cadeia", "WS", "COMMENT", "INVALID_KEYWORD", "UNKNOWN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GYH.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GYHParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public List<TerminalNode> Delim() { return getTokens(GYHParser.Delim); }
		public TerminalNode Delim(int i) {
			return getToken(GYHParser.Delim, i);
		}
		public TerminalNode PCDec() { return getToken(GYHParser.PCDec, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode PCProg() { return getToken(GYHParser.PCProg, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GYHParser.EOF, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(Delim);
			setState(37);
			match(PCDec);
			setState(38);
			declarations();
			setState(39);
			match(Delim);
			setState(40);
			match(PCProg);
			setState(41);
			statementList();
			setState(42);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationsContext extends ParserRuleContext {
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitDeclarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Var) {
				{
				{
				setState(44);
				variableDeclaration();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GYHParser.Var, 0); }
		public TerminalNode Delim() { return getToken(GYHParser.Delim, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(Var);
			setState(51);
			match(Delim);
			setState(52);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode PCInt() { return getToken(GYHParser.PCInt, 0); }
		public TerminalNode PCReal() { return getToken(GYHParser.PCReal, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !(_la==PCInt || _la==PCReal) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementListContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitStatementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		StatementListContext _localctx = new StatementListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 536874208L) != 0)) {
				{
				{
				setState(56);
				statement();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ReadCommandContext readCommand() {
			return getRuleContext(ReadCommandContext.class,0);
		}
		public PrintCommandContext printCommand() {
			return getRuleContext(PrintCommandContext.class,0);
		}
		public IfCommandContext ifCommand() {
			return getRuleContext(IfCommandContext.class,0);
		}
		public WhileCommandContext whileCommand() {
			return getRuleContext(WhileCommandContext.class,0);
		}
		public SubAlgorithmContext subAlgorithm() {
			return getRuleContext(SubAlgorithmContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				assignment();
				}
				break;
			case PCLer:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				readCommand();
				}
				break;
			case PCImprimir:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				printCommand();
				}
				break;
			case PCSe:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				ifCommand();
				}
				break;
			case PCEnqto:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				whileCommand();
				}
				break;
			case PCIni:
				enterOuterAlt(_localctx, 6);
				{
				setState(67);
				subAlgorithm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GYHParser.Var, 0); }
		public TerminalNode Atrib() { return getToken(GYHParser.Atrib, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(Var);
			setState(71);
			match(Atrib);
			setState(72);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadCommandContext extends ParserRuleContext {
		public TerminalNode PCLer() { return getToken(GYHParser.PCLer, 0); }
		public TerminalNode Var() { return getToken(GYHParser.Var, 0); }
		public ReadCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterReadCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitReadCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitReadCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadCommandContext readCommand() throws RecognitionException {
		ReadCommandContext _localctx = new ReadCommandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_readCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(PCLer);
			setState(75);
			match(Var);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintCommandContext extends ParserRuleContext {
		public TerminalNode PCImprimir() { return getToken(GYHParser.PCImprimir, 0); }
		public TerminalNode Cadeia() { return getToken(GYHParser.Cadeia, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterPrintCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitPrintCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitPrintCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintCommandContext printCommand() throws RecognitionException {
		PrintCommandContext _localctx = new PrintCommandContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_printCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(PCImprimir);
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Cadeia:
				{
				setState(78);
				match(Cadeia);
				}
				break;
			case AbrePar:
			case Var:
			case NumReal:
			case NumInt:
				{
				setState(79);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfCommandContext extends ParserRuleContext {
		public TerminalNode PCSe() { return getToken(GYHParser.PCSe, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PCEntao() { return getToken(GYHParser.PCEntao, 0); }
		public List<StatementListContext> statementList() {
			return getRuleContexts(StatementListContext.class);
		}
		public StatementListContext statementList(int i) {
			return getRuleContext(StatementListContext.class,i);
		}
		public TerminalNode PCFim() { return getToken(GYHParser.PCFim, 0); }
		public TerminalNode PCSenao() { return getToken(GYHParser.PCSenao, 0); }
		public IfCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterIfCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitIfCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitIfCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCommandContext ifCommand() throws RecognitionException {
		IfCommandContext _localctx = new IfCommandContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(PCSe);
			setState(83);
			expression();
			setState(84);
			match(PCEntao);
			setState(85);
			statementList();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PCSenao) {
				{
				setState(86);
				match(PCSenao);
				setState(87);
				statementList();
				}
			}

			setState(90);
			match(PCFim);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileCommandContext extends ParserRuleContext {
		public TerminalNode PCEnqto() { return getToken(GYHParser.PCEnqto, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SubAlgorithmContext subAlgorithm() {
			return getRuleContext(SubAlgorithmContext.class,0);
		}
		public WhileCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterWhileCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitWhileCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitWhileCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileCommandContext whileCommand() throws RecognitionException {
		WhileCommandContext _localctx = new WhileCommandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_whileCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(PCEnqto);
			setState(93);
			expression();
			setState(94);
			subAlgorithm();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubAlgorithmContext extends ParserRuleContext {
		public TerminalNode PCIni() { return getToken(GYHParser.PCIni, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public TerminalNode PCFim() { return getToken(GYHParser.PCFim, 0); }
		public SubAlgorithmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subAlgorithm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterSubAlgorithm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitSubAlgorithm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitSubAlgorithm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubAlgorithmContext subAlgorithm() throws RecognitionException {
		SubAlgorithmContext _localctx = new SubAlgorithmContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_subAlgorithm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(PCIni);
			setState(97);
			statementList();
			setState(98);
			match(PCFim);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public LogicalExpressionContext logicalExpression() {
			return getRuleContext(LogicalExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			logicalExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> OpBoolE() { return getTokens(GYHParser.OpBoolE); }
		public TerminalNode OpBoolE(int i) {
			return getToken(GYHParser.OpBoolE, i);
		}
		public List<TerminalNode> OpBoolOu() { return getTokens(GYHParser.OpBoolOu); }
		public TerminalNode OpBoolOu(int i) {
			return getToken(GYHParser.OpBoolOu, i);
		}
		public LogicalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalExpressionContext logicalExpression() throws RecognitionException {
		LogicalExpressionContext _localctx = new LogicalExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_logicalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			relationalExpression();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpBoolE || _la==OpBoolOu) {
				{
				{
				setState(103);
				_la = _input.LA(1);
				if ( !(_la==OpBoolE || _la==OpBoolOu) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(104);
				relationalExpression();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public List<TerminalNode> OpRelMaior() { return getTokens(GYHParser.OpRelMaior); }
		public TerminalNode OpRelMaior(int i) {
			return getToken(GYHParser.OpRelMaior, i);
		}
		public List<TerminalNode> OpRelMaiorIgual() { return getTokens(GYHParser.OpRelMaiorIgual); }
		public TerminalNode OpRelMaiorIgual(int i) {
			return getToken(GYHParser.OpRelMaiorIgual, i);
		}
		public List<TerminalNode> OpRelMenor() { return getTokens(GYHParser.OpRelMenor); }
		public TerminalNode OpRelMenor(int i) {
			return getToken(GYHParser.OpRelMenor, i);
		}
		public List<TerminalNode> OpRelMenorIgual() { return getTokens(GYHParser.OpRelMenorIgual); }
		public TerminalNode OpRelMenorIgual(int i) {
			return getToken(GYHParser.OpRelMenorIgual, i);
		}
		public List<TerminalNode> OpRelIgual() { return getTokens(GYHParser.OpRelIgual); }
		public TerminalNode OpRelIgual(int i) {
			return getToken(GYHParser.OpRelIgual, i);
		}
		public List<TerminalNode> OpRelDif() { return getTokens(GYHParser.OpRelDif); }
		public TerminalNode OpRelDif(int i) {
			return getToken(GYHParser.OpRelDif, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_relationalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			arithmeticExpression();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33030144L) != 0)) {
				{
				{
				setState(111);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 33030144L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(112);
				arithmeticExpression();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticExpressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> OpAritSoma() { return getTokens(GYHParser.OpAritSoma); }
		public TerminalNode OpAritSoma(int i) {
			return getToken(GYHParser.OpAritSoma, i);
		}
		public List<TerminalNode> OpAritSub() { return getTokens(GYHParser.OpAritSub); }
		public TerminalNode OpAritSub(int i) {
			return getToken(GYHParser.OpAritSub, i);
		}
		public ArithmeticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitArithmeticExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitArithmeticExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arithmeticExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			term();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritSoma || _la==OpAritSub) {
				{
				{
				setState(119);
				_la = _input.LA(1);
				if ( !(_la==OpAritSoma || _la==OpAritSub) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(120);
				term();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> OpAritMult() { return getTokens(GYHParser.OpAritMult); }
		public TerminalNode OpAritMult(int i) {
			return getToken(GYHParser.OpAritMult, i);
		}
		public List<TerminalNode> OpAritDiv() { return getTokens(GYHParser.OpAritDiv); }
		public TerminalNode OpAritDiv(int i) {
			return getToken(GYHParser.OpAritDiv, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			factor();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritMult || _la==OpAritDiv) {
				{
				{
				setState(127);
				_la = _input.LA(1);
				if ( !(_la==OpAritMult || _la==OpAritDiv) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(128);
				factor();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GYHParser.Var, 0); }
		public TerminalNode NumInt() { return getToken(GYHParser.NumInt, 0); }
		public TerminalNode NumReal() { return getToken(GYHParser.NumReal, 0); }
		public TerminalNode AbrePar() { return getToken(GYHParser.AbrePar, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GYHParser.FechaPar, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GYHListener ) ((GYHListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GYHVisitor ) return ((GYHVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_factor);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				match(Var);
				}
				break;
			case NumInt:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(NumInt);
				}
				break;
			case NumReal:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				match(NumReal);
				}
				break;
			case AbrePar:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(AbrePar);
				setState(138);
				expression();
				setState(139);
				match(FechaPar);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u0090\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0005\u0001.\b\u0001\n\u0001\f\u00011\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0005\u0004:\b\u0004\n\u0004\f\u0004=\t\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005E\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\bQ\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\tY\b\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005\rj\b\r\n\r\f\rm\t\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000er\b\u000e\n\u000e\f\u000eu\t\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000fz\b\u000f\n\u000f\f\u000f"+
		"}\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0082\b\u0010"+
		"\n\u0010\f\u0010\u0085\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u008e\b\u0011\u0001"+
		"\u0011\u0000\u0000\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"\u0000\u0005\u0001\u0000\u0003"+
		"\u0004\u0001\u0000\r\u000e\u0001\u0000\u0013\u0018\u0001\u0000\u0011\u0012"+
		"\u0001\u0000\u000f\u0010\u008d\u0000$\u0001\u0000\u0000\u0000\u0002/\u0001"+
		"\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000\u00066\u0001\u0000\u0000"+
		"\u0000\b;\u0001\u0000\u0000\u0000\nD\u0001\u0000\u0000\u0000\fF\u0001"+
		"\u0000\u0000\u0000\u000eJ\u0001\u0000\u0000\u0000\u0010M\u0001\u0000\u0000"+
		"\u0000\u0012R\u0001\u0000\u0000\u0000\u0014\\\u0001\u0000\u0000\u0000"+
		"\u0016`\u0001\u0000\u0000\u0000\u0018d\u0001\u0000\u0000\u0000\u001af"+
		"\u0001\u0000\u0000\u0000\u001cn\u0001\u0000\u0000\u0000\u001ev\u0001\u0000"+
		"\u0000\u0000 ~\u0001\u0000\u0000\u0000\"\u008d\u0001\u0000\u0000\u0000"+
		"$%\u0005\u0019\u0000\u0000%&\u0005\u0001\u0000\u0000&\'\u0003\u0002\u0001"+
		"\u0000\'(\u0005\u0019\u0000\u0000()\u0005\u0002\u0000\u0000)*\u0003\b"+
		"\u0004\u0000*+\u0005\u0000\u0000\u0001+\u0001\u0001\u0000\u0000\u0000"+
		",.\u0003\u0004\u0002\u0000-,\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000"+
		"\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000\u0003\u0001"+
		"\u0000\u0000\u00001/\u0001\u0000\u0000\u000023\u0005\u001d\u0000\u0000"+
		"34\u0005\u0019\u0000\u000045\u0003\u0006\u0003\u00005\u0005\u0001\u0000"+
		"\u0000\u000067\u0007\u0000\u0000\u00007\u0007\u0001\u0000\u0000\u0000"+
		"8:\u0003\n\u0005\u000098\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000"+
		";9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<\t\u0001\u0000\u0000"+
		"\u0000=;\u0001\u0000\u0000\u0000>E\u0003\f\u0006\u0000?E\u0003\u000e\u0007"+
		"\u0000@E\u0003\u0010\b\u0000AE\u0003\u0012\t\u0000BE\u0003\u0014\n\u0000"+
		"CE\u0003\u0016\u000b\u0000D>\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000"+
		"\u0000D@\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000\u0000DB\u0001\u0000"+
		"\u0000\u0000DC\u0001\u0000\u0000\u0000E\u000b\u0001\u0000\u0000\u0000"+
		"FG\u0005\u001d\u0000\u0000GH\u0005\u001a\u0000\u0000HI\u0003\u0018\f\u0000"+
		"I\r\u0001\u0000\u0000\u0000JK\u0005\u0005\u0000\u0000KL\u0005\u001d\u0000"+
		"\u0000L\u000f\u0001\u0000\u0000\u0000MP\u0005\u0006\u0000\u0000NQ\u0005"+
		" \u0000\u0000OQ\u0003\u0018\f\u0000PN\u0001\u0000\u0000\u0000PO\u0001"+
		"\u0000\u0000\u0000Q\u0011\u0001\u0000\u0000\u0000RS\u0005\u0007\u0000"+
		"\u0000ST\u0003\u0018\f\u0000TU\u0005\b\u0000\u0000UX\u0003\b\u0004\u0000"+
		"VW\u0005\t\u0000\u0000WY\u0003\b\u0004\u0000XV\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0005\f\u0000\u0000"+
		"[\u0013\u0001\u0000\u0000\u0000\\]\u0005\n\u0000\u0000]^\u0003\u0018\f"+
		"\u0000^_\u0003\u0016\u000b\u0000_\u0015\u0001\u0000\u0000\u0000`a\u0005"+
		"\u000b\u0000\u0000ab\u0003\b\u0004\u0000bc\u0005\f\u0000\u0000c\u0017"+
		"\u0001\u0000\u0000\u0000de\u0003\u001a\r\u0000e\u0019\u0001\u0000\u0000"+
		"\u0000fk\u0003\u001c\u000e\u0000gh\u0007\u0001\u0000\u0000hj\u0003\u001c"+
		"\u000e\u0000ig\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000l\u001b\u0001\u0000\u0000"+
		"\u0000mk\u0001\u0000\u0000\u0000ns\u0003\u001e\u000f\u0000op\u0007\u0002"+
		"\u0000\u0000pr\u0003\u001e\u000f\u0000qo\u0001\u0000\u0000\u0000ru\u0001"+
		"\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000"+
		"t\u001d\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000v{\u0003 \u0010"+
		"\u0000wx\u0007\u0003\u0000\u0000xz\u0003 \u0010\u0000yw\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|\u001f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"~\u0083\u0003\"\u0011\u0000\u007f\u0080\u0007\u0004\u0000\u0000\u0080"+
		"\u0082\u0003\"\u0011\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0082\u0085"+
		"\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0001\u0000\u0000\u0000\u0084!\u0001\u0000\u0000\u0000\u0085\u0083\u0001"+
		"\u0000\u0000\u0000\u0086\u008e\u0005\u001d\u0000\u0000\u0087\u008e\u0005"+
		"\u001f\u0000\u0000\u0088\u008e\u0005\u001e\u0000\u0000\u0089\u008a\u0005"+
		"\u001b\u0000\u0000\u008a\u008b\u0003\u0018\f\u0000\u008b\u008c\u0005\u001c"+
		"\u0000\u0000\u008c\u008e\u0001\u0000\u0000\u0000\u008d\u0086\u0001\u0000"+
		"\u0000\u0000\u008d\u0087\u0001\u0000\u0000\u0000\u008d\u0088\u0001\u0000"+
		"\u0000\u0000\u008d\u0089\u0001\u0000\u0000\u0000\u008e#\u0001\u0000\u0000"+
		"\u0000\n/;DPXks{\u0083\u008d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}