import java.util.*;

public class BuildOrder{
  public static class Project{
    public char item;
    private Set<Project> dependsOn;
    private Set<Project> dependents;

    public Project(char item){
      this.item = item;
      dependsOn = new HashSet();
      dependents = new HashSet();
    }

    public void addDependsOn(Project project){
      dependsOn.add(project);
    }

    public Set<Project> getDependsOn(){
      return dependsOn;
    }

    public Set<Project> getDependents(){
      return dependents;
    }

    public void addDependents(Project project){
      dependents.add(project);
    }
  }

  public static class DependentPair{
    public Project dependent;
    public Project dependsOn;

    public DependentPair(Project dependent, Project dependsOn){
      this.dependent = dependent;
      this.dependsOn = dependsOn;
    }
  }

  public void printBuildOrder(Set<Project> projects, Set<DependentPair> pairs) throws Exception {
    for(DependentPair pair : pairs){
      pair.dependent.addDependsOn(pair.dependsOn);
      pair.dependsOn.addDependents(pair.dependent);
    }

    Set<Project> occured = new HashSet<Project>();

    for(Project project : projects)
      if(project.getDependents().size() == 0 && project.getDependsOn().size() == 0){
        System.out.println(project.item);
        occured.add(project);
      }

    Project firstProject = null;
    for(Project project : projects)
      if(!occured.contains(project) && project.getDependsOn().size() == 0)
        firstProject = project;

    if(firstProject == null) throw new Exception();
    printBuildOrderStep(firstProject, occured, projects);
  }

  private void printBuildOrderStep(Project project, Set<Project> occured, Set<Project> projects) throws Exception{
    System.out.println(project.item);
    occured.add(project);
    if(projects.size() == occured.size()) return;
    Project nextProject = findNextProject(projects, occured);
    if(nextProject == null) throw new Exception();
    printBuildOrderStep(nextProject, occured, projects);
  }

  private Project findNextProject(Set<Project> projects, Set<Project> occured){
    for(Project project : projects){
      if(!occured.contains(project) && occured.containsAll(project.getDependsOn())){
        return project;
      }
    }
    return null;
  }

  public static void main(String[] args){
    final Project a = new Project('a');
    final Project b = new Project('b');
    final Project c = new Project('c');
    final Project d = new Project('d');
    final Project e = new Project('e');
    final Project f = new Project('f');

    final DependentPair pair1 = new DependentPair(d, a);
    final DependentPair pair2 = new DependentPair(b, f);
    final DependentPair pair3 = new DependentPair(d, b);
    final DependentPair pair4 = new DependentPair(a, f);
    final DependentPair pair5 = new DependentPair(c, d);

    try{
      new BuildOrder().printBuildOrder(new HashSet<Project>(){{
        add(a);
        add(b);
        add(c);
        add(d);
        add(e);
        add(f);
      }}, new HashSet<DependentPair>(){{
        add(pair1);
        add(pair2);
        add(pair3);
        add(pair4);
        add(pair5);
      }});
    } catch(Exception ex){
      System.out.println(ex);
    }

  }
}