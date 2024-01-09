package org.rdf;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import java.io.IOException;
import java.io.StringWriter;

public class RDFMapper extends Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split(",");
        if (parts.length == 2) {
            String patientNameWithId = parts[0]; // Patient name includes the unique ID
            String diagnosis = parts[1];

            String patientURI = "http://hospital.org/patients/" + patientNameWithId.replaceAll("\\s+", "_");
            String diagnosisPropertyURI = "http://hospital.org/ontology/hasDiagnosis";

            // Create RDF model for this record
            Model model = ModelFactory.createDefaultModel();
            Resource patient = model.createResource(patientURI)
                    .addProperty(model.createProperty(diagnosisPropertyURI), diagnosis);

            // Convert the model to a string (RDF/XML format)
            String rdfOutput = convertModelToString(model);
            context.write(new Text(patientURI), new Text(rdfOutput));
        }
    }

    private String convertModelToString(Model model) {
        // Serialize the model to a string in RDF/XML format
        StringWriter out = new StringWriter();
        model.write(out, "RDF/XML");
        return out.toString();
    }
}
