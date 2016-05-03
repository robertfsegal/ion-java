package myIonJavaTestProject;

import java.io.IOException;
import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonWriter;
import software.amazon.ion.system.*;

public class myIonJavaTestProject {

	static final IonSystem SYSTEM = IonSystemBuilder.standard().build();
	static final String textJson = "{ level1:{ level2:{ level3:\"foo\" }, x:2 }, y:[a,b,c] }";
	static final String textIon = "{ data:annot::{ foo:null.string, bar:(2 + 2) }, time:1969-07-20T20:18Z }";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// downconvertToJson();
			prettyPrint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void rewrite(String text, IonWriter writer) throws IOException {
		IonReader reader = SYSTEM.newReader(textIon);
		writer.writeValues(reader);

		IonWriter iw = SYSTEM.newBinaryWriter(null, null);

	}

	static void prettyPrint() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (IonWriter prettyWriter = IonTextWriterBuilder.pretty().build(
				stringBuilder)) {
			rewrite(textJson, prettyWriter);
		}
		System.out.println(stringBuilder.toString());
	}

	static void downconvertToJson() throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		try (IonWriter jsonWriter = IonTextWriterBuilder.json()
				.withPrettyPrinting().build(stringBuilder)) {
			rewrite(textIon, jsonWriter);
		}
		System.out.println(stringBuilder.toString());
	}
}
