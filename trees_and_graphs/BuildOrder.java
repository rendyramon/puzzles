import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BuildOrder{
  public static class Project{
    public char item;
    private Set<Project> dependsOn;
    private Set<Project> dependents;
    public boolean visited;

    public Project(char item){
      this.item = item;
      dependsOn = new HashSet();
      dependents = new HashSet();
      visited = false;
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
    if(projects.size() == 0) return;

    for(DependentPair pair : pairs){
      pair.dependent.addDependsOn(pair.dependsOn);
      pair.dependsOn.addDependents(pair.dependent);
    }
    
    printUnreachables(projects);
    if(projects.size() == 0) return;

    Project firstProject = findFirstProject(projects.iterator().next());
    if(firstProject == null) throw new Exception();

    printBuildOrderStep(firstProject);
  }

  private void printUnreachables(Set<Project> projects){
    Iterator<Project> iter = projects.iterator();
    while(iter.hasNext()){
      Project project = iter.next();
      if(project.getDependents().size() == 0 && project.getDependsOn().size() == 0){
        System.out.println(project.item);
        iter.remove();
      }
    }
  }
  private void printBuildOrderStep(Project project) throws Exception{
    Queue<Project> projectsQueue = new ConcurrentLinkedQueue();
    projectsQueue.add(project);
    while(!projectsQueue.isEmpty()){
      Project currentProject = projectsQueue.remove();
      System.out.println(currentProject.item);
      currentProject.visited = true;
      for(Project dependent : currentProject.getDependents())
        if(!dependent.visited){
          dependent.visited = true;
          projectsQueue.add(dependent);
        }
    }
  }

  private Project findFirstProject(Project project){
    Stack<Project> projectsStack = new Stack<>();
    projectsStack.push(project);
    while (!projectsStack.isEmpty()) {
      Project currentProject = projectsStack.pop();
      if(currentProject.getDependsOn().size() == 0)
        return currentProject;
      else{
        for(Project dependsOn : currentProject.getDependsOn())
          projectsStack.push(dependsOn);
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