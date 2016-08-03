public class AnimalShelter{
  public static abstract class Animal{
    public int id;
    public String name;
  }

  public static class Dog extends Animal{
    public Dog(String name){
      this.name = name;
    }
  }

  public static class Cat extends Animal{
    public Cat(String name){
      this.name = name;
    }
  }

  public static class Queue<T>{
    public static class QueueNode<T>{
      public T item;
      public QueueNode<T> next;

      public QueueNode(T item){
        this.item = item;
      }
    }

    public static class QueueEmptyException extends Exception{
      public QueueEmptyException(){
        super();
      }
    }

    public QueueNode<T> first;
    public QueueNode<T> last;

    public void enqueue(T item){
      QueueNode<T> node = new QueueNode<>(item);
      if(first == null){
        first = node;
        last = node;
        return;
      }

      last.next = node;
      last = last.next;
    }

    public T dequeue() throws Exception{
      if(isEmpty()) throw new QueueEmptyException();

      QueueNode<T> node = first;
      first = first.next;
      if(first == null) last = null;
      return node.item;
    }

    public T peek() throws Exception{
      if(isEmpty()) throw new QueueEmptyException();
      return first.item;
    }

    public boolean isEmpty(){
      return first == null;
    }
  }

  int currentTotal;
  Queue<Dog> dogQueue = new Queue<>();
  Queue<Cat> catQueue = new Queue<>();
  
  public AnimalShelter(){
    this.currentTotal = 0;
  }

  public Dog dequeueDog() throws Exception{
    return dogQueue.dequeue();
  }

  public Cat dequeueCat() throws Exception{
    return catQueue.dequeue();
  }

  public Animal dequeueAny() throws Exception{
    if(dogQueue.peek().id < catQueue.peek().id){
      return dogQueue.dequeue();
    }
    return catQueue.dequeue();
  }

  public void enqueue(Animal animal){
    animal.id = currentTotal;
    currentTotal++;

    if(animal instanceof Dog){
      dogQueue.enqueue((Dog)animal);
    } else if (animal instanceof Cat){
      catQueue.enqueue((Cat)animal);
    }
  }

  public static void main(String[] args){
    AnimalShelter shelter = new AnimalShelter();

    shelter.enqueue(new Dog("Scooby"));
    shelter.enqueue(new Dog("Snoopy"));
    shelter.enqueue(new Dog("Shoshan"));

    shelter.enqueue(new Cat("Tinkerbell"));
    shelter.enqueue(new Cat("Snuffles"));
    shelter.enqueue(new Cat("Bob"));

    try{
      System.out.println(shelter.dequeueAny().name);
      System.out.println(shelter.dequeueAny().name);
      System.out.println(shelter.dequeueCat().name);
      System.out.println(shelter.dequeueAny().name);
    } catch(Exception e){
      System.out.println(e);
    }
  }

}