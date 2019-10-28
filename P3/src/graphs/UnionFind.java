package graphs;

public class UnionFind
{
  int[] parent;
  int[] height;
  int components;

  public UnionFind(int n)
  {
    if (n < 0)
    {
      System.out.print("Error! n is 0 or less.");
    }

    parent = new int[n];
    height = new int[n];

    for (int i = 0; i < n; i++)
    {
      parent[i] = i;
      height[i] = 0;
    }
    components = n;
  }

  public int components()
  {
    return components;
  }

  public int find(int v)
  {
    while (v != parent[v]) {
      parent[v] = parent[parent[v]];
      v = parent[v];
  }
  return v;
  }

  public void union(int u, int v)
  {
    int i = find(u);
    int j = find(v);
    
    if (i==j) {
      return;
    }
    
    //parent[i] = j;
    //so if height of 1st > 2nd parent == 1st
    if (height[i] > height[j]) {
      parent[j] = i;
    }
    //if height of 1st < 2nd parent == 1st parent = 2nd
    else if (height[i] < height[j]) {
      parent[i] = j;
    }
    //else it they are the same - 1st is going to be the parent of the 2nd 
    //2nd increments the height of the first one
    else {
      parent[j] = i;
      height[i]++;
    }
    components--;

  }

  public boolean connected(int u, int v)
  {
    return (find(u) == find(v));
  }

  public double meanHeight()
  {
    double mean = 0;
    for (int i = 0; i < height.length; i++) {
      mean += (double) height[i];
    }
    mean /= (double) height.length;
    
    return mean;
  }

}
