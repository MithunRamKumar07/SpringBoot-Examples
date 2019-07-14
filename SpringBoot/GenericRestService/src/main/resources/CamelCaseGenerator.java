
public class CamelCaseGenerator {

		public static enum Token {

			// Enum Constant
			SPACE(" "), COMMA(","), DOT("\\."), DASH("-"), UNDERSCORE("_");

			private String token;

			Token(String token) {

				this.token = token;
			}

			public String getToken() {

				return token;
			}
		}

		public static String camelCase(String str, Token token) {

			String result = "";

			if (str != null && !str.isEmpty()) {

				String[] words = str.split(token.getToken());

				for (int i = 0; i < words.length; i++) {

					if(i == 0) {
						result += words[i].toLowerCase(); // converting first word to lower case
					} else {
						result += words[i].substring(0, 1).toUpperCase() + words[i].substring(1); // converting remaining words are capitalize
					}
				}
			}

			return result;
		}

		public static void main(String[] args) {

			System.out.println(camelCase("\"Cust_No\": \"23423\",\r\n" + 
					"					\"Short_Name\": \"Short.Name1673948\",\r\n" + 
					"					\"Is_Individual\": \"Y\",\r\n" + 
					"					\"Nationality\": \"Mu\",\r\n" + 
					"					\"Nationality_Num\": \"480\",\r\n" + 
					"					\"Nationality_Desc\": \"Mauritius\",\r\n" + 
					"					\"Street_Addr\": \"Street-Addr\",\r\n" + 
					"					\"Address_Line2\": [],\r\n" + 
					"					\"Address_Line3\": [],\r\n" + 
					"					\"Town_Country\": \"Curepipe\",\r\n" + 
					"					\"Post_Code\": [],\r\n" + 
					"					\"Country\": \"Country\",\r\n" + 
					"					\"Country_Code\": \"Mu\",\r\n" + 
					"					\"Country_Code_Num\": \"480\",\r\n" + 
					"					\"Despatch_Code\": \"Ma\",", Token.UNDERSCORE));

		}

	}
}
