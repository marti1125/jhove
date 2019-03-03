/* Generated By:JavaCC: Do not edit this line. ParseHtml.java */
/** Caution: Changes made by hand to fix bugs.  Be sure to reenter
 *  these fixes if the file is regenerated.
 */
package edu.harvard.hul.ois.jhove.module.html;

import java.util.LinkedList;
import java.util.List;

import edu.harvard.hul.ois.jhove.module.HtmlModule;

public class ParseHtml implements ParseHtmlConstants {
        private List elements;

        public List getElements ()
        {
            return elements;
        }

  final public List HtmlDoc() throws ParseException {
        elements = new LinkedList ();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STARTDOCTYPE:
      case LABRACKET:
      case PCDATA:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      Element(elements);
    }
    jj_consume_token(0);
      {if (true) return elements;}
    throw new Error("Missing return statement in function");
  }

  final public JHElement Element(List elements) {
    JHElement elem;
    try {
      if (jj_2_1(2)) {
        elem = Doctype();
                            {if (true) return elem;}
      } else if (jj_2_2(2)) {
        elem = OpenTag();
                           {if (true) return elem;}
      } else if (jj_2_3(2)) {
        elem = CloseTag();
                            {if (true) return elem;}
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PCDATA:
          elem = PCData();
                          {if (true) return elem;}
          break;
        default:
          jj_la1[1] = jj_gen;
          if (jj_2_4(2)) {
            elem = XMLDecl();
                           {if (true) return elem;}
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    } catch (ParseException e) {
        StringBuilder errText = new StringBuilder();
        for (;;) {
            token_source.SwitchTo(DEFAULT);
            Token tok = getNextToken ();
            if (tok.kind == LABRACKET || tok.kind == PCDATA) {
                break;
            }
            errText.append("Text = \"").append(tok.image).append("\", Line = ")
                .append(tok.beginLine).append(", Column = ").append(tok.beginColumn);
            /****** Added GDM 14-Jun-05 to avoid infinite loop ********/
            if ("".equals (tok.image)) {
                break;
            }
            /******* End Added GDM 14-Jun-05 to avoid infinite loop ********/
        }
        {if (true) return new JHErrorElement(elements, module.getMessageFactory().getMessage("HTML-HUL-2"), errText.toString(), true);}
    }
      {if (true) return elem;}
    throw new Error("Missing return statement in function");
  }

  final public JHOpenTag OpenTag() throws ParseException {
    List attrs = new LinkedList ();
    Token name;
    String slasher;
    jj_consume_token(LABRACKET);
    name = Name();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NAME:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      Attribute(attrs);
    }
    slasher = TagCloser();
      if ("/".equals (slasher)) {
         /* This is a special hack so that a tag closed with "/>" will keep
            the whole thing from falling apart, yet will generate an error */
         {if (true) return new JHOpenTag (elements, name.image, attrs,
           name.beginLine, name.beginColumn,
           module.getMessageFactory().getMessage("HTML-HUL-1").message);}
     }
     else {
         {if (true) return new JHOpenTag (elements, name.image, attrs,
            name.beginLine, name.beginColumn);}
     }
    throw new Error("Missing return statement in function");
  }

  final public JHXmlDecl XMLDecl() throws ParseException {
    List attrs = new LinkedList ();
    jj_consume_token(LABRACKET);
    jj_consume_token(QMARK);
    jj_consume_token(NAME);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NAME:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      Attribute(attrs);
    }
    jj_consume_token(QMARK);
    jj_consume_token(RABRACKET);
           {if (true) return new JHXmlDecl (elements);}
    throw new Error("Missing return statement in function");
  }

  final public JHCloseTag CloseTag() throws ParseException {
    Token name;
    jj_consume_token(LABRACKET);
    jj_consume_token(SLASH);
    name = Name();
    jj_consume_token(RABRACKET);
      {if (true) return new JHCloseTag (elements, name.image,
              name.beginLine, name.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public JHPCData PCData() throws ParseException {
    Token tok = getToken(1);
    jj_consume_token(PCDATA);
               {if (true) return new JHPCData (elements, tok.image, tok.beginLine, tok.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public JHDoctype Doctype() throws ParseException {
    List doctypeElements = new LinkedList ();
    jj_consume_token(STARTDOCTYPE);
    jj_consume_token(DOCTYPEKEYWORD);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NAKEDDTITEM:
      case QUOTEDDTITEM:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      DoctypeItem(doctypeElements);
    }
    jj_consume_token(RABRACKET);
     {if (true) return new JHDoctype (elements, doctypeElements);}
    throw new Error("Missing return statement in function");
  }

  JHErrorElement ConsumeError() {
    Token tok = getNextToken();
        return new JHErrorElement (elements, module.getMessageFactory().getMessage("HTML-HUL-3"), tok.image, true);
  }

  final public void DoctypeItem(List dtElements) throws ParseException {
    Token tok = getToken(1);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAKEDDTITEM:
      jj_consume_token(NAKEDDTITEM);
                    dtElements.add (tok.image);
      break;
    case QUOTEDDTITEM:
      jj_consume_token(QUOTEDDTITEM);
                     dtElements.add (tok.image);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public Token Name() throws ParseException {
    Token tok = getToken(1);
    jj_consume_token(NAME);
             {if (true) return tok;}
    throw new Error("Missing return statement in function");
  }

  final public String AttrVal() throws ParseException {
    Token tok = getToken(1);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAKEDVALUE:
      jj_consume_token(NAKEDVALUE);
                   {if (true) return tok.image;}
      break;
    case QUOTEDVALUE:
      jj_consume_token(QUOTEDVALUE);
                    {if (true) return tok.image;}
      jj_consume_token(SINGQUOTEDVALUE);
                        {if (true) return tok.image;}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void Attribute(List attrs) throws ParseException {
    JHAttribute attval;
    Token name;
    Token namespace;
    String val;
    if (jj_2_5(2)) {
      namespace = Name();
      jj_consume_token(COLON);
      name = Name();
      jj_consume_token(EQUALS);
      val = AttrVal();
      attval = new JHAttribute (name.image, namespace.image, val,
                name.beginLine, name.beginColumn);
      attrs.add(attval);
    } else if (jj_2_6(2)) {
      namespace = Name();
      jj_consume_token(COLON);
      name = Name();
      attval = new JHAttribute (name.image, namespace.image,
            null,
                name.beginLine, name.beginColumn);
      attrs.add(attval);
    } else if (jj_2_7(2)) {
      name = Name();
      jj_consume_token(EQUALS);
      val = AttrVal();
      attval = new JHAttribute (name.image, null, val,
            name.beginLine, name.beginColumn);
      attrs.add(attval);
    } else if (jj_2_8(2)) {
      name = Name();
      attval = new JHAttribute (name.image, null, null,
            name.beginLine, name.beginColumn);
      attrs.add(attval);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public String TagCloser() throws ParseException {
    Token tok = getToken (1);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SLASH:
      jj_consume_token(SLASH);
      jj_consume_token(RABRACKET);
                              {if (true) return tok.image;}
      break;
    case RABRACKET:
      jj_consume_token(RABRACKET);
                   {if (true) return tok.image;}
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  final private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  final private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  final private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  final private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  final private boolean jj_3_6() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  final private boolean jj_3R_9() {
    if (jj_scan_token(NAME)) return true;
    return false;
  }

  final private boolean jj_3R_7() {
    if (jj_scan_token(LABRACKET)) return true;
    if (jj_scan_token(SLASH)) return true;
    return false;
  }

  final private boolean jj_3_5() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  final private boolean jj_3R_8() {
    if (jj_scan_token(LABRACKET)) return true;
    if (jj_scan_token(QMARK)) return true;
    return false;
  }

  final private boolean jj_3R_5() {
    if (jj_scan_token(STARTDOCTYPE)) return true;
    if (jj_scan_token(DOCTYPEKEYWORD)) return true;
    return false;
  }

  final private boolean jj_3R_6() {
    if (jj_scan_token(LABRACKET)) return true;
    if (jj_3R_9()) return true;
    return false;
  }

  final private boolean jj_3_8() {
    if (jj_3R_9()) return true;
    return false;
  }

  final private boolean jj_3_4() {
    if (jj_3R_8()) return true;
    return false;
  }

  final private boolean jj_3_3() {
    if (jj_3R_7()) return true;
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_3R_6()) return true;
    return false;
  }

  final private boolean jj_3_7() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(EQUALS)) return true;
    return false;
  }

  final private boolean jj_3_1() {
    if (jj_3R_5()) return true;
    return false;
  }
  private final HtmlModule module;
  public ParseHtmlTokenManager token_source;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_0();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x16,0x10,0x20,0x20,0x18000,0x18000,0x180,0x2800,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[8];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public ParseHtml(HtmlModule module, CharStream stream) {
    this.module = module;
    token_source = new ParseHtmlTokenManager(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(CharStream stream) {
    token_source.ReInit(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public ParseHtml(ParseHtmlTokenManager tm, HtmlModule module) {
    this.module = module;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(ParseHtmlTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[27];
    for (int i = 0; i < 27; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 27; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 8; i++) {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
          }
        }
        p = p.next;
      } while (p != null);
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
