package code.jtduan.email;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jintaoduan on 17/4/10.
 */
public class SendEmail {
    public static class Table{
        private String title;
        private String columnTitle;
        private List<String> rows = new ArrayList<>();

        private Table(){
        }

        public static Table newInstance(){
            return new Table();
        }

        public String build(){
            StringBuilder sb = new StringBuilder();
            sb.append("<p>").append(title).append("</p>");
            sb.append("<table class='altrowstable'>");
            sb.append(columnTitle);
            for(String row:rows) {
                sb.append(row);
            }
            sb.append("</table><br />");
            return sb.toString();
        }
        public Table addRow(String... cells){
            StringBuilder sb = new StringBuilder();
            sb.append("<tr>");
            for(String cell:cells){
                sb.append("<td>"+cell+"</td>");
            }
            sb.append("</tr>");
            rows.add(sb.toString());
            return this;
        }

        public Table setColumnTitle(String... titles){
            StringBuilder sb = new StringBuilder();
            sb.append("<tr bgcolor=\"#fcfaf2\">");
            for(String title:titles){
                sb.append("<th>"+title+"</th>");
            }
            sb.append("</tr>");
            columnTitle = sb.toString();
            return this;
        }
        public Table setTitle(String title){
            this.title=title;
            return this;
        }
    }
}
