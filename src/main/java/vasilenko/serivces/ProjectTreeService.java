package vasilenko.serivces;

import vasilenko.serivces.dto.TreeNode;

import java.util.List;


public interface ProjectTreeService {
    public List<TreeNode> getProjectTree(int projectId);
}
