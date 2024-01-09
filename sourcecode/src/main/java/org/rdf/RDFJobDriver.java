package org.rdf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RDFJobDriver {

    public static void main(String[] args) throws Exception {
        // if (args.length != 2) {
        //     System.err.println("Usage: RDFJobDriver <input path> <output path>");
        //     System.exit(-1);
        // }

        Configuration conf = new Configuration();

        // Test with local file system
//        conf.set("mapreduce.framework.name", "local"); // Set to local mode
//        conf.set("fs.defaultFS", "file:///"); // Use local file system

        Job job = Job.getInstance(conf, "RDF Conversion Job");
        job.setJarByClass(RDFJobDriver.class);
        job.setMapperClass(RDFMapper.class);
        job.setNumReduceTasks(0); // No reduce tasks
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

