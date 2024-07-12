import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private Map<String, Folder> nameFolderMap;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        nameFolderMap = new TreeMap<>();
        int totalCount = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        for (int i = 0; i < totalCount; i++) {
            st = new StringTokenizer(bf.readLine());
            String parentFolder = st.nextToken();
            String fileName = st.nextToken();
            boolean isFolder = st.nextToken().equals("1");
            Folder parent = nameFolderMap.get(parentFolder);
            if (parent == null) {
                parent = new Folder(parentFolder);
                nameFolderMap.put(parentFolder, parent);
            }
            if (isFolder) {
                Folder child = nameFolderMap.getOrDefault(fileName, new Folder(fileName));
                nameFolderMap.put(fileName, child);
                parent.addFolder(child);
            } else {
                File child = new File(fileName);
                parent.addFile(child);
            }
        }
    }

    private String calcAns() throws IOException {
        int queryCount = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            String[] query = bf.readLine().split("/");
            String folderName = query[query.length - 1];
            Folder folder = nameFolderMap.get(folderName);
            sb.append(folder.getFileCount2())
                    .append(" ")
                    .append(folder.getFileCount())
                    .append("\n");
        }
        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    static class File {
        private final String name;

        public File(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            File file = (File) o;
            return Objects.equals(name, file.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Folder extends File {
        private final Set<Folder> folders = new HashSet<>();
        private final Set<File> files = new HashSet<>();

        public Folder(String name) {
            super(name);
        }

        public void addFolder(Folder folder) {
            folders.add(folder);
        }

        public void addFile(File file) {
            files.add(file);
        }

        public int getFileCount() {
            int count = files.size();
            for (Folder folder : folders) {
                count += folder.getFileCount();
            }
            return count;
        }

        public int getFileCount2() {
            Set<File> allFiles = new HashSet<>();
            return getAllFile(allFiles).size();
        }

        private Set<File> getAllFile(Set<File> allFiles) {
            allFiles.addAll(files);
            for (Folder folder : folders) {
                folder.getAllFile(allFiles);
            }
            return allFiles;
        }

        @Override
        public String toString() {
            return "Folder{" +
                    "name='" + super.name + '\'' +
                    ", files=" + files +
                    '}';
        }
    }
}
