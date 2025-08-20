class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1 - Build in degree array and graph
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[numCourses];
        Arrays.fill(indegree, 0);
        for(int i=0;i<prerequisites.length;i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            if(!graph.containsKey(b)) {
                graph.put(b, new ArrayList());
            }
            // map independent to dependent
            graph.get(b).add(a);

            // increment indegree
            indegree[a]++;
        }

        // Add all the independent nodes to the queue
        Queue<Integer> queue = new LinkedList();
        int independentCourses = 0;
        for(int i=0;i<numCourses;i++) {
            if(indegree[i]==0){
                queue.add(i);
                independentCourses++;
            }
        }
        
        if(queue.size()==numCourses) return true;

        // Iterate through the independent courses and decrement the associated dependent courses. If at all the dependent ones reaches zero indegree, add it to queue
        while(!queue.isEmpty()) {    
            int num = queue.poll();
            if(!graph.containsKey(num)) continue;
            List<Integer> list = graph.get(num);
            for(int i=0;i<list.size();i++) {
                indegree[list.get(i)]--;
                if(indegree[list.get(i)]==0) {
                    independentCourses++;
                    queue.add(list.get(i));
                }
            }
        }
        if(independentCourses==numCourses) return true;
        return false;
     }
}

