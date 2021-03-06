/* expr
 Parses strings in the language generated by the rule:
 <expr> -> <term> {(+ | -) <term>}
 */
void expr(void) {
	 printf("Enter <expr>\n");
	/* Parse the first term */
	 term();
	/* As long as the next token is + or -, get
	 the next token and parse the next term */
	 while (nextToken == ADD_OP || nextToken == SUB_OP || nextToken == MUL_OP || nextToken == MOD_OP || nextToken == DIV_OP || nextToken == PREV_INC || nextToken == AFT_INC || nextToken == PREV_DEC || nextToken == AFT_DEC) {
		 lex();
		 term();
	 }
	 printf("Exit <expr>\n");
}

/* bexp
 Parses strings in the language generated by the rule:
 <bexp> -> <term> {(> | <  | || | &&  | <= | >= | = | != |/= | !| ~|) <term>}
 */
void bexp(void) {
	printf("Enter <expr>\n");
	/* Parse the first term */
	term();
	/* As long as the next token is + or -, get
	 the next token and parse the next term */
	while (nextToken == GREATER || nextToken == LESSER || nextToken == EQUAL || nextToken == GERAT_EQUAL || nextToken == LESS_EQUAL || nextToken == LOGIC_AND || nextToken == LOGIC_OR || nextToken == AND || nextToken == OR || nextToken == NOT || nextToken == NOT_EUQAL || nextToken == LOGIC_EQUAL || nextTOken == TILDE_FOLLOWED_BY_A_PIPE) {
		lex();
		term();
	}
	printf("Exit <bexp>\n");
}




/* term
 Parses strings in the language generated by the rule:
 <term> -> <factor> {(* | /) <factor>}
 */
void term(void) {
	 printf("Enter <term>\n");
	/* Parse the first factor */
	 factor();
	/* As long as the next token is * or /, get the
	 next token and parse the next factor */
	 while (nextToken == MULT_OP || nextToken == DIV_OP) {
		 lex();
		 factor();
	 }
	 printf("Exit <term>\n");
}

/* factor
 Parses strings in the language generated by the rule:
 <factor> -> id | int_constant | ( <expr> )
 */
void factor(void) {
	 printf("Enter <factor>\n");
	/* Determine which RHS */
	 if (nextToken == IDENT || nextToken == INT_LIT)
		/* Get the next token */
		 lex();
		/* If the RHS is ( <expr> ), call lex to pass over the
		 left parenthesis, call expr, and check for the right
		 parenthesis */
	 else { if (nextToken == LEFT_PAREN) {
		 lex();
		 expr();
		 if (nextToken == RIGHT_PAREN)
		 	lex();
		 else
		 	error();
		 }
		/* It was not an id, an integer literal, or a left
		 parenthesis */
		 else
		 error();
	 }
	 printf("Exit <factor>\n");
}



/****

Trace of the parse of (sum + 47) / total:
Next token is: 25, Next lexeme is (
Enter <expr>
Enter <term>
Enter <factor>
Next token is: 11, Next lexeme is sum
Enter <expr>
Enter <term>
Enter <factor>
Next token is: 21, Next lexeme is +
Exit <factor>
Exit <term>
Next token is: 10, Next lexeme is 47
Enter <term>
Enter <factor>
Next token is: 26, Next lexeme is )
Exit <factor>
Exit <term>
Exit <expr>
Next token is: 24, Next lexeme is /
Exit <factor>
Next token is: 11, Next lexeme is total
Enter <factor>
Next token is: -1, Next lexeme is EOF
Exit <factor>
Exit <term>
Exit <expr>

****/